package br.edu.ifsp.scl.fazpramim.model

import br.edu.ifsp.scl.fazpramim.controller.response.UserResponse
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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    var user: UserModel
)
