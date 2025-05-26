package com.aurum.services.microservice.domain

import com.aurum.services.microservice.domain.com.aurum.services.microservice.domain.Service
import javax.persistence.*

@Entity
@Table(name = "servico_contratado")
data class ContractedService(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @ManyToOne
    @JoinColumn(name = "id_servico", nullable = false)
    var service: Service,

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    var client: Client,

    @Column(name = "qtde", nullable = false)
    var quantity: Int,

    @Column(name = "status", nullable = false)
    var status: String
)
