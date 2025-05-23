package com.aurum.services.microservice.rootproject.log

import com.aurum.services.microservice.application.log.ILogger
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class Logger : ILogger {

    private val logger = KotlinLogging.logger {}

    override fun debug(message:String) {
        logger.debug(message)
    }

    override fun info(message: String) {
        logger.info(message)
    }

    override fun warn(message: String) {
        logger.warn(message)
    }

    override fun error(t:Throwable?, message: () -> Any?) {
        logger.error(t, message);
    }
}