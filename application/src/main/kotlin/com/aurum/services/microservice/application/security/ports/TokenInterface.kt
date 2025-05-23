package com.aurum.services.microservice.application.security.ports

import com.aurum.services.microservice.application.security.exception.NamespaceNotFoundException

interface TokenInterface {
    fun getNamespaceOrNull() : String?
    fun getNamespace() : String {
        return getNamespaceOrNull() ?: throw NamespaceNotFoundException()
    }
}