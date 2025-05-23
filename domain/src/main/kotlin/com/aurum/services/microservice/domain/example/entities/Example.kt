package com.aurum.services.microservice.domain.example.entities

import com.aurum.services.microservice.domain.example.exception.ExampleNotValidException

class Example (initValue : String?) {
    val name: String

    init {
        if (initValue.isNullOrBlank()){
            throw ExampleNotValidException.INVALID_NAME
        }
        name = initValue
    }
}