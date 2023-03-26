package br.edu.ifsp.scl.fazpramim.controller.request

import br.edu.ifsp.scl.fazpramim.enums.ProfileType
import br.edu.ifsp.scl.fazpramim.validation.EmailAvailable
import com.fasterxml.jackson.annotation.JsonAlias
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class PostPersonRequest (

    @field:NotEmpty(message = "Nome deve ser informado.")
    var name: String,

    @field:Email(message = "E-mail deve ser válido.")
    @EmailAvailable
    var email: String,

    @field:NotEmpty(message = "Senha deve ser informada.")
    var password: String,

    var phone: String,

    var photo: String,

    var profileType: ProfileType,

    @JsonAlias("birth_date")
    var birthDate: String,
)
