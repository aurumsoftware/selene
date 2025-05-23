package com.aurum.services.microservice.security.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("project")
class ProjectConfig {
    lateinit var name: String
    lateinit var environment: String
}