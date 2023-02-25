package br.edu.ifsp.scl.fazpramim.model

import br.edu.ifsp.scl.fazpramim.validation.EmailAvailable
import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import org.springframework.format.annotation.DateTimeFormat

@Entity
@Table(name = "person")
data class Person(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = 0,

    @field:NotEmpty(message = "Nome deve ser informado.")
    @Column(nullable = false)
    var name: String = "",

    @field:Email(message = "E-mail deve ser válido.")
    @EmailAvailable
    @Column(nullable = false, unique = true)
    var email: String = "",

    @Column(nullable = false)
    var password: String = "",

    @Column(nullable = false)
    var phone: String = "",

    @Column(nullable = false, updatable = false)
    var birthDate: String = ""
)

