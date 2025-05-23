package com.aurum.services.microservice.application.queue

interface Enqueuer<T> {
    fun enqueue(message: T)
}