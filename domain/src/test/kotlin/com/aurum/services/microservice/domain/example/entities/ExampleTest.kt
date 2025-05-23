package com.aurum.services.microservice.domain.example.entities

import com.aurum.services.microservice.domain.example.exception.ExampleNotValidException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

internal class ExampleTest{

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = ["      "])
    fun `should must throw exception if name is Null Or Empty`(name : String?) {
        val exception = assertThrows(ExampleNotValidException::class.java) {
            Example(name)
        }
        assertEquals(ExampleNotValidException.INVALID_NAME.message, exception.message)
    }
}