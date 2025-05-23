package com.aurum.services.microservice.queue.rabbitmq.exceptions

class RabbitMQEnqueuerException constructor(override val message: String?): Exception(message)