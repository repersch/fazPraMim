package br.edu.ifsp.scl.fazpramim.model

import jakarta.persistence.*

@Entity
@Table(name = "services")
data class ServiceModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    var client: UserModel,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    var provider: ProfileModel,
)
