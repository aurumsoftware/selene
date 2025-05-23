package com.aurum.services.microservice.security.properties

import com.aurum.services.microservice.application.security.ports.ApplicationPropertiesInterface
import org.springframework.stereotype.Component

@Component
class ApplicationPropertiesService (
        private val serverConfig: ServerConfig
        ): ApplicationPropertiesInterface {

    override fun getContextPath(): String = serverConfig.servletContextPath

}