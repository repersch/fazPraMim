package br.edu.ifsp.scl.fazpramim.model

import jakarta.persistence.*

@Entity
@Table(name = "profiles_person")
data class ProfileModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var description: String = "",

    @Column
    var city: String = ""
)
