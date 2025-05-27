package com.aurum.services.microservice.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "evento")
data class Event(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    var client: Client,

    @ManyToOne
    @JoinColumn(name = "id_fornecedor", nullable = false)
    var supplier: Supplier,

    @Column(name = "tipo", nullable = false)
    var type: String,

    @Column(name = "feature", nullable = false)
    var feature: String,

    @Column(name = "id_origem", nullable = false)
    var originId: Long,

    @Column(name = "data_criacao", nullable = false)
    var creationDate: LocalDateTime
)
