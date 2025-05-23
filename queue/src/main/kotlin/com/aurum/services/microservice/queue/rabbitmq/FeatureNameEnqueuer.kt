package com.aurum.services.microservice.queue.rabbitmq

import com.aurum.services.microservice.application.log.ILogger
import org.springframework.stereotype.Service

@Service
class FeatureNameEnqueuer(
    logger: ILogger
) : RabbitMQEnqueuer(logger) {
    override fun queueName() = "queue-name"
}