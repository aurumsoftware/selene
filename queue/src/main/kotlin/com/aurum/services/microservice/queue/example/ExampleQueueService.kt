package com.aurum.services.microservice.queue.example

import com.aurum.services.microservice.application.example.port.ExampleInterfaceCallbackStringQueue
import com.aurum.services.microservice.domain.example.entities.ExampleDomain
import org.springframework.stereotype.Component

@Component
class ExampleQueueService : ExampleInterfaceCallbackStringQueue {
    override fun getCallbackFromHttpModule(exampleDomain: ExampleDomain): ExampleDomain {
        return exampleDomain
    }
}