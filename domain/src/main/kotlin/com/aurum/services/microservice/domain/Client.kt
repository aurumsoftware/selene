package com.aurum.services.microservice.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "cliente")
data class Client(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(name = "nome", nullable = false)
    var name: String,

    @Column(name = "numero_serie", length = 15)
    var serialNumber: String? = null,

    @Column(name = "api_key")
    var apiKey: String? = null,

    @Column(name = "status", nullable = false)
    var status: String,

    @Column(name = "data_criacao", nullable = false)
    var creationDate: LocalDateTime?,

    @Column(name = "data_atualizacao")
    var updateDate: LocalDateTime?,

    @Column(name = "sincronizado", nullable = false)
    var synchronizedFlag: String
)
