package br.edu.ifsp.scl.fazpramim.model

import jakarta.persistence.*

@Entity
@Table(name = "person")
data class Person(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = 0,

    @Column(nullable = false)
    var name: String = "",

    @Column(nullable = false, unique = true)
    var email: String = "",

    @Column(nullable = false)
    var password: String = "",

    @Column(nullable = false)
    var phone: String = "",

    @Column(nullable = false, updatable = false)
    var birthDate: String = ""
)

