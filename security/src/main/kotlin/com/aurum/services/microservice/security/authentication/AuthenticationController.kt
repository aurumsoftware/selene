package com.aurum.services.microservice.security.authentication

import com.aurum.services.microservice.security.config.UserConfig
import com.aurum.services.microservice.security.jwt.JwtTokenGenerator
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.Objects.nonNull

//@Tag(name = "Autenticação no selene")
@RestController
class AuthenticationController(
    @Value("\${auth.TTLInMinutes}")
    private val TTLInMinutes: Int
) {
    @Autowired
    lateinit var userConfig: UserConfig

    @Value("\${tokens.SELENE_TOKEN}")
    lateinit var secret: String

    @PostMapping("/login")
//    @Operation(summary = "Devolve o token para um usuário válido")
    fun authenticate(@RequestBody login: String): ResponseEntity<String> {
        val loginDTO = Gson().fromJson(login, LoginDTO::class.java)
        return if (validateUser(loginDTO)) {
            val token = JwtTokenGenerator.createToken(loginDTO.username, secret, TTLInMinutes.toLong())
            ResponseEntity.status(HttpStatus.OK)
                .header("X-Aurum-Auth", "Bearer $token")
                .build()
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .build()
        }
    }

    fun validateUser(loginDTO: LoginDTO): Boolean {
        val storedPassword = userConfig.getUsersMap()[loginDTO.username]
        return nonNull(storedPassword) && loginDTO.password.equals(storedPassword)
    }

}