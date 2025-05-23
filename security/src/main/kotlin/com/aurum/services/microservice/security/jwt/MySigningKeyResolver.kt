package com.aurum.services.microservice.security.jwt

import com.aurum.services.microservice.security.provider.SecretManagerProvider
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwsHeader
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.SigningKeyResolverAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.security.Key
import javax.crypto.spec.SecretKeySpec

@Component
class MySigningKeyResolver : SigningKeyResolverAdapter() {

    @Autowired
    private val secretManagerProvider: SecretManagerProvider? = null

    override fun resolveSigningKey(jwsHeader: JwsHeader<*>, claims: Claims): Key {

        val secretName = getSecretName(claims.audience, claims.issuer)
        val secret = secretManagerProvider?.getSecret(secretName) ?: ""
        return SecretKeySpec(secret.toByteArray(), 0, secret.length, SignatureAlgorithm.HS256.jcaName)
    }



    private fun getSecretName(aud : String, iss : String) : String {
        return "${aud}_${iss}_JWT_TOKEN"
    }
}