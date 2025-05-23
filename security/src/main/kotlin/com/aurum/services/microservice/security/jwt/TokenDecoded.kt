package com.aurum.services.microservice.security.jwt

data class TokenDecoded(
        val validated: Boolean = false,
        val claims : Map<String, Any>
)