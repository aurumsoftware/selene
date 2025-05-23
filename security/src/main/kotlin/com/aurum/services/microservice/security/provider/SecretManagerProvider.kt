package com.aurum.services.microservice.security.provider

import com.aurum.services.microservice.security.properties.SecretsConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

data class SecretVersion(val value: String, val rotationDateTime: LocalDateTime?)

@Component
class SecretManagerProvider @Autowired constructor(
    private val secretConfig: SecretsConfig
) {
    companion object {

        private val secretsMap = mutableMapOf<String, SecretVersion>()

        @JvmStatic
        fun set(secretName: String, secretValue: SecretVersion): SecretVersion {
            secretsMap.set(secretName, secretValue).let { return secretValue }
        }

        @JvmStatic
        fun get(secretName: String): SecretVersion? {
            return secretsMap.containsKey(secretName)
                .let { secretsMap[secretName] }
                ?.takeIf { it.isValid() }
        }

        private fun SecretVersion.isValid() =
            this.rotationDateTime == null || this.rotationDateTime.isAfter(Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime())

    }

    fun getSecret(secretName: String): String? {
        return secretConfig.MICROSERVICE_TEMPLATE_TOKEN
    }

}