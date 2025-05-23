package com.aurum.services.microservice.controller.example.exception


class ExampleRequestException private constructor(exception: String) : RuntimeException(exception) {
    companion object {
        val EMPTY_NAME = ExampleRequestException("you shall not pass")
    }
}