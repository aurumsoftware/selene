package com.aurum.services.microservice.repository.repositories

import com.aurum.services.microservice.application.example.port.IExampleRepository
import com.aurum.services.microservice.domain.example.entities.ExampleDomain
import com.aurum.services.microservice.repository.entities.Example
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

class ExampleRepositoryClass : IExampleRepository {

    @Autowired
    private lateinit var mongoTemplate: MongoTemplate

    override fun findByStringDomain(name: String): ExampleDomain? {
        return Query()
            .addCriteria(Criteria.where("stringDomain").`in`(name))
            .let{ mongoTemplate.findOne(it, Example::class.java) }
            ?.let { ExampleDomain(it.stringDomain) }
    }

}