package com.aurum.services.microservice.controller.config.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApplicationControllerAdvice {
    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun globalExceptionError(ex: Exception): HttpResponseError {
        ex.printStackTrace()
        return HttpResponseError(ex.message ?:"Erro interno não identificado", HttpStatus.INTERNAL_SERVER_ERROR.name)
    }

}