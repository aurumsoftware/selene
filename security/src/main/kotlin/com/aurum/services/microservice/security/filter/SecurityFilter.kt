package com.aurum.services.microservice.security.filter

import com.aurum.services.microservice.security.config.DefaultUser
import com.aurum.services.microservice.security.jwt.CurrentToken
import com.aurum.services.microservice.security.jwt.JwtTokenUtil
import com.aurum.services.microservice.security.jwt.TokenDecoded
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class SecurityFilter @Autowired constructor(
    private val jwtToken: JwtTokenUtil,
    private val currentToken: CurrentToken
): OncePerRequestFilter() {


    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest,
                                  response: HttpServletResponse,
                                  chain: FilterChain) {
        verifyTokenAndAuthenticated(request)
        chain.doFilter(request, response)
    }



    private fun verifyTokenAndAuthenticated(request: HttpServletRequest) {
        val header = request.getHeader(HttpHeaders.AUTHORIZATION)
        if(header?.startsWith("Bearer ") == true){
            val tokenString = getToken(header)
            val tokenDecoded = validateToken(tokenString)
            if(!tokenDecoded.validated) return
            storeCurrentToken(tokenString)
            authenticate(request)
        }
    }

    private fun validateToken(token: String ) : TokenDecoded = jwtToken.validateToken(token)

    private fun getToken(header: String): String {
        return header.split(" ")
                .toTypedArray()[1].trim { it <= ' ' }
    }

    private fun storeCurrentToken(token: String){
        currentToken.currentToken = token
    }

    private fun authenticate(request: HttpServletRequest) {

        val authentication = UsernamePasswordAuthenticationToken(DefaultUser().user, DefaultUser().password)
        authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authentication
    }

}