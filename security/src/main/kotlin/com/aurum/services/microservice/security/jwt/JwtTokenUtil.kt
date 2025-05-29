package com.aurum.services.microservice.security.jwt

import com.aurum.services.microservice.application.log.ILogger
import com.aurum.services.microservice.application.security.ports.TokenInterface
import com.aurum.services.microservice.security.filter.AuthenticationInterface
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Component
class JwtTokenUtil @Autowired constructor(
    private val mySigningKeyResolver: MySigningKeyResolver,
    private val currentToken: CurrentToken,
    private val iLogger: ILogger,
    @Value("\${auth.TTLInMinutes}")
    private val TTLInMinutes: Long
) : AuthenticationInterface, TokenInterface {


    override fun validateToken(token: String): TokenDecoded {
        return try {
            val jwtDecoded = getTokenDecoded(token)
            val validated = validateTimeToken(jwtDecoded.expiration)
            TokenDecoded(validated, jwtDecoded)
        } catch (e: Exception) {
            iLogger.error(e) { "Unauthorized error: ${e.message} for token: $token " }
            TokenDecoded(false, emptyMap())
        }
    }

    private fun getTokenDecoded(token: String): Claims {
        return Jwts.parser()
            .setSigningKeyResolver(mySigningKeyResolver)
            .parseClaimsJws(token).body
    }

    private fun validateTimeToken(expirationTime: Date?): Boolean {
        val maxTimeInLocalTime = LocalDateTime.now().plusHours(TTLInMinutes)
        val maxTimeInDate = Date.from(maxTimeInLocalTime.atZone(ZoneId.systemDefault()).toInstant())
        return expirationTime != null && expirationTime.before(maxTimeInDate)
    }

    override fun getNamespaceOrNull(): String? {
        val tokenString = currentToken.currentToken
        return if (!tokenString.isNullOrBlank()) getTokenDecoded(tokenString)["namespace"] as String? else null
    }

}