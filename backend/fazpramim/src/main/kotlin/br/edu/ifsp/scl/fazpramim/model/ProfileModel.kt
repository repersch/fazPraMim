package br.edu.ifsp.scl.fazpramim.model

import br.edu.ifsp.scl.fazpramim.controller.response.UserResponse
import br.edu.ifsp.scl.fazpramim.service.ServiceService
import jakarta.persistence.*

@Entity
@Table(name = "profiles_user")
data class ProfileModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column
    var description: String = "",

    @Column
    var city: String = "",

    @Column
    var rating: Int = 0,

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    var user: UserModel,

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_type_id")
    var serviceType: ServiceTypeModel,

    @OneToMany(mappedBy = "provider", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val services: MutableSet<ServiceModel> = mutableSetOf()
)
