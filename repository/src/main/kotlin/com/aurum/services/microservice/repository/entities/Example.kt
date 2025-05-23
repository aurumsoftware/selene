package com.aurum.services.microservice.repository.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Example(
    @Id val id: String? = null,
    val stringDomain: String,
)