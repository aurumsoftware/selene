package com.aurum.services.microservice.http.example

import com.aurum.services.microservice.application.example.port.ExampleInterfaceHttp
import org.springframework.stereotype.Component

@Component
class ExampleHttpService : ExampleInterfaceHttp {
    override fun getHelloWorldFromHttpModule(): String {
        return "Hello World"
    }
}