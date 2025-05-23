package com.aurum.services.microservice.security.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("secretmanager")
class SecretManagerConfig {
    lateinit var credentials: String
}