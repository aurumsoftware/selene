package com.aurum.services.microservice.queue.rabbitmq

import com.aurum.services.microservice.application.queue.Enqueuer
import com.aurum.services.microservice.queue.rabbitmq.exceptions.RabbitMQEnqueuerException
import com.rabbitmq.client.Channel
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.MessageProperties
import com.aurum.services.microservice.application.log.ILogger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
abstract class RabbitMQEnqueuer @Autowired constructor(
    private val logger: ILogger
): Enqueuer<List<Long>> {
    override fun enqueue(message: List<Long>) {
        logger.info("Enqueing $message")
        runCatching {
            val factory = ConnectionFactory()
            factory.host = "url"
            factory.username = "user"
            factory.password = "password"
            factory.requestedHeartbeat = 5

            factory.newConnection().use { connection ->
                connection.createChannel().use { channel ->
                    channel.queueDeclare(queueName(), true, false, false, null)
                        sendMessages(channel, message)
                }
            }
        }.onFailure {
            logger.error(it) { it.stackTraceToString() }
            throw RabbitMQEnqueuerException(it.message)
        }
    }

    private fun sendMessages(channel: Channel, offices: List<Long>?) {
        offices?.forEach {
            channel.basicPublish(
                "",
                queueName(),
                MessageProperties.PERSISTENT_TEXT_PLAIN,
                it.toString().toByteArray()
            )
            logger.info(" [x] Sent '$it'")
        }
    }

    abstract fun queueName(): String
}