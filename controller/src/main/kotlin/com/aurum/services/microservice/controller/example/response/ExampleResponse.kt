package com.aurum.services.microservice.controller.example.response

import com.aurum.services.microservice.domain.example.entities.ExampleDomain

data class ExampleResponse(
        val response : String
) {
    constructor(exampleDomain: ExampleDomain) : this(exampleDomain.stringDomain)
}
