package com.aurum.services.microservice.domain


import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "movimentacao_credito")
data class MovimentacaoCredito(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @ManyToOne
    @JoinColumn(name = "id_servico_contratado", nullable = false)
    var contractedService: ContractedService,

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    var type: TypeOfCreditMovement,

    @Column(name = "valor", nullable = false)
    var value: Long,

    @Column(name = "data_movimentacao", nullable = false)
    var movementDate: LocalDateTime,

    @Column(name = "sincronizacao", nullable = false)
    var synchronized: String,

    @Column(name = "data_sincronizacao")
    var syncDate: LocalDateTime?,

    @Column(name = "origem", nullable = false)
    var origin: String,

    @Column(name = "observacao")
    var observation: String?,

    @ManyToOne
    @JoinColumn(name = "id_movimento_origem")
    var originMovement: MovimentacaoCredito?
)
