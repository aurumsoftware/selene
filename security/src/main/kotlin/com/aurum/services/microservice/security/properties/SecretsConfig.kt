package com.aurum.services.microservice.security.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("tokens")
class SecretsConfig {
    lateinit var MICROSERVICE_TEMPLATE_TOKEN: String
}