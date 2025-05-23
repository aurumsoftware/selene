package com.aurum.services.microservice.security.properties

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ServerConfig {

    @Value("\${server.servlet.context-path}")
    lateinit var servletContextPath: String
}