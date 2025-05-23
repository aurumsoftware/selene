package com.aurum.services.microservice.controller.example.request

import com.aurum.services.microservice.domain.example.entities.ExampleDomain

data class ExampleRequest(
        val request : String
) {
    fun getExampleDomain() : ExampleDomain = ExampleDomain(request)
}
