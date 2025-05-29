package com.aurum.services.microservice.security.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTCreator
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.*

@Component
class JwtTokenGenerator {
    companion object {
        fun createToken(userLogin: String, secret: String, TTLInMinutes: Long): String? {
            val basicToken = JWT.create()
                .withIssuer(userLogin)
                .withAudience("Selene")
            return basicToken.sign(getExpiredDate(TTLInMinutes), secret)
        }

        private fun getExpiredDate(TTLInMinutes: Long): Date = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).plus(TTLInMinutes, ChronoUnit.MINUTES).toInstant())
        private fun JWTCreator.Builder.sign(expiredDate: Date, secret: String) = this
            .withIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
            .withExpiresAt(expiredDate)
            .sign(Algorithm.HMAC256(secret))
    }
}