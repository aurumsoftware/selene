package com.aurum.services.microservice.security.provider

import com.aurum.services.microservice.security.properties.SecretManagerConfig
import com.google.api.gax.core.FixedCredentialsProvider
import com.google.auth.oauth2.ServiceAccountCredentials
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient
import com.google.cloud.secretmanager.v1.SecretManagerServiceSettings
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.ByteArrayInputStream

@Component
class SecretManagerLoader @Autowired constructor(
        private val secretManagerConfig: SecretManagerConfig
) {

    fun getSecretManagerServiceClient() : SecretManagerServiceClient {
        return SecretManagerServiceClient.create(secretManagerServiceSettings);
    }

    private val credential get() = ServiceAccountCredentials.fromStream(ByteArrayInputStream(secretManagerConfig.credentials.toByteArray()))

    private val secretManagerServiceSettings get() = SecretManagerServiceSettings.newBuilder()
        .setCredentialsProvider(FixedCredentialsProvider.create(credential))
        .build()

}