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

    @field:NotEmpty(message = "Nome deve ser informado.")
    @Column
    var name: String,

    @field:Email(message = "E-mail deve ser válido.")
    @Column
    var email: String,

    @field:NotEmpty(message = "Senha deve ser informada.")
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
