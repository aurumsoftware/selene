package com.aurum.services.microservice.controller.config.exception

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class HttpResponseError(val message: String, val status: String) {
    val data: String = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"))

}
