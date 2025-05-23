package com.aurum.services.microservice.domain.example.exception


class ExampleNotValidException private constructor(exception: String) : RuntimeException(exception) {
    companion object {
        val INVALID_NAME = ExampleNotValidException("Did you think it would pass?")
    }
}