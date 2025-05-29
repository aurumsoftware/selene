package com.aurum.services.microservice.security.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class UserConfig {
    @Value("\${user.default}")
    lateinit var defaultUsername: String

    @Value("\${password.default}")
    lateinit var defaultPassword: String

    fun getUsersMap(): Map<String, String> {
        return mapOf(
            defaultUsername to defaultPassword
        )
    }
}