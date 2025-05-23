package com.aurum.services.microservice.repository.repositories

import com.aurum.services.microservice.application.example.port.IExampleRepository
import com.aurum.services.microservice.repository.entities.Example
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ExampleRepository : MongoRepository<Example, String>,
    IExampleRepository