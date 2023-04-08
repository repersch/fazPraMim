package br.edu.ifsp.scl.fazpramim.model

import jakarta.persistence.*

@Entity
@Table(name = "service_type")
data class ServiceTypeModel (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column
    var description: String? = null
)
