package com.aurum.services.microservice.application.security.ports

interface PropertiesInterface {
    fun getRootBucketName() : String
    fun getCredentials(): String
}