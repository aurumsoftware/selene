package com.aurum.services.microservice.domain.com.aurum.services.microservice.domain

import com.aurum.services.microservice.domain.Supplier
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "servico")
data class Service(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(name = "id_item_contrato", nullable = false)
    var contractItemId: Long,

    @ManyToOne
    @JoinColumn(name = "id_fornecedor", nullable = false)
    var supplier: Supplier,

    @Column(name = "nome", nullable = false)
    var name: String
)