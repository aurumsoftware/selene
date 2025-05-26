package com.aurum.services.microservice.domain

import javax.persistence.*

@Entity
@Table(name = "fornecedor")
data class Supplier(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(name = "nome", nullable = false)
    var name: String,

    @Column(name = "status", nullable = false)
    var status: String
)
