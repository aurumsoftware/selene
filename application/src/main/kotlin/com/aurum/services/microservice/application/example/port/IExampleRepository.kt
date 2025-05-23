package com.aurum.services.microservice.application.example.port

import com.aurum.services.microservice.domain.example.entities.ExampleDomain

interface IExampleRepository {
    fun findByStringDomain(mimeType: String): ExampleDomain?
}