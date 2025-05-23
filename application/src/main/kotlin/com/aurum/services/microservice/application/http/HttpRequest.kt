package com.aurum.services.microservice.application.http

interface HttpRequest {
    fun fetchOffices(): List<Long>?
}