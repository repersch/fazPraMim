package br.edu.ifsp.scl.fazpramim.controller.request

import br.edu.ifsp.scl.fazpramim.enums.ProfileType
import br.edu.ifsp.scl.fazpramim.validation.EmailAvailable
import com.fasterxml.jackson.annotation.JsonAlias
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class PostUserRequest (

    var id: Long?,

    @field:NotEmpty(message = "Nome deve ser informado.")
    var fullName: String,

    @field:Email(message = "E-mail deve ser válido.")
    @EmailAvailable
    var username: String,

    @field:NotEmpty(message = "Senha deve ser informada.")
    var password: String,

    var phone: String,

    var photo: String,

    @JsonAlias("birth_date")
    var birthDate: String,

    @JsonAlias("user_id")
    var userId: Long,

    @JsonAlias("profile_type")
    var profileType: ProfileType?
)
