package br.edu.ifsp.scl.fazpramim.model

import br.edu.ifsp.scl.fazpramim.enums.ServiceStatus
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "services")
data class ServiceModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "service_status")
    @Enumerated(EnumType.STRING)
    var status: ServiceStatus,

    @Column
    var date: LocalDate,

    @Column
    var hour: String,

    @Column(name = "request_details")
    var requestDetails: String,

    @Column
    var rating: Int = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    var client: UserModel,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    var provider: ProfileModel,
)
