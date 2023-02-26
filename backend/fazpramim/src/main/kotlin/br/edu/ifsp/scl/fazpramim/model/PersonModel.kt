package br.edu.ifsp.scl.fazpramim.model

import br.edu.ifsp.scl.fazpramim.enums.PersonStatus
import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

@Entity(name = "person")
data class PersonModel (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    var email: String,

    @Column
    var password: String,

    @Column
    var phone: String,

    @Column
    var birthDate: String,

    @Column(name = "person_status")
    @Enumerated(EnumType.STRING)
    var personStatus: PersonStatus?
) {
    constructor() : this(null, "", "", "", "", "", null) {}
}
