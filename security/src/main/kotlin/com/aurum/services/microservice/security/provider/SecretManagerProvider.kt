package com.aurum.services.microservice.security.provider

import com.aurum.services.microservice.application.log.ILogger
import com.aurum.services.microservice.security.config.EnvironmentType
import com.aurum.services.microservice.security.properties.ProjectConfig
import com.aurum.services.microservice.security.properties.SecretsConfig
import com.google.api.gax.rpc.ApiException
import com.google.cloud.secretmanager.v1.Rotation
import com.google.cloud.secretmanager.v1.SecretName
import com.google.cloud.secretmanager.v1.SecretVersionName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

data class SecretVersion(val value: String, val rotationDateTime: LocalDateTime?)

@Component
class SecretManagerProvider @Autowired constructor(
    private val projectConfig: ProjectConfig,
    private val secretConfig: SecretsConfig,
    private val secretManagerLoader: SecretManagerLoader,
    private val iLogger: ILogger
) {
    private val client by lazy { if (isLocalEnv()) null else secretManagerLoader.getSecretManagerServiceClient() }

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

        if (isLocalEnv()) return secretConfig.MICROSERVICE_TEMPLATE_TOKEN

        return get(secretName)?.value
                ?: loadLatestSecretFromManager(secretName)
                                ?.let { set(secretName, it) }?.value
    }

    private fun loadLatestSecretFromManager(secretName: String): SecretVersion? {
        try {
            client.use { client ->
                val secretLoaded = client!!.accessSecretVersion(secretName.secretManagerSecretVersionName)

                val rotationDateTime = client.getSecret(secretName.secretManagerSecretName).rotation
                                ?.takeIf { it.nextRotationTime.toString().isNotEmpty() }?.localDateTime

                return SecretVersion(secretLoaded.payload.data.toStringUtf8(), rotationDateTime)
            }
        } catch (e: ApiException) {
            iLogger.error(e) { "Error on trying to fetch $secretName in project ${projectConfig.name}. " }
            return null
        }
    }

    private fun isLocalEnv(): Boolean {
        return projectConfig.environment == EnvironmentType.LOCAL.name
    }

    private val String.secretManagerSecretVersionName get() = SecretVersionName.of(projectConfig.name, this, "latest")

    private val String.secretManagerSecretName get() = SecretName.of(projectConfig.name, this)

    private val Rotation.localDateTime get() =
        Instant.ofEpochSecond(this.nextRotationTime.seconds, this.nextRotationTime.nanos.toLong())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()

}