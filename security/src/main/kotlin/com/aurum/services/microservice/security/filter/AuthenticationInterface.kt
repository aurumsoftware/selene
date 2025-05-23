package com.aurum.services.microservice.security.filter

import com.aurum.services.microservice.security.jwt.TokenDecoded

interface AuthenticationInterface {
    fun validateToken(token : String) : TokenDecoded
}

