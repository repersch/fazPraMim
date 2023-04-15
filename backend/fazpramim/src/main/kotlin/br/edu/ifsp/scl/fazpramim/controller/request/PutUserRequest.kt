package br.edu.ifsp.scl.fazpramim.controller.request

import br.edu.ifsp.scl.fazpramim.enums.ProfileType
import com.fasterxml.jackson.annotation.JsonAlias

data class PutUserRequest (
    var id: Long?,
    var fullName: String?,
    var username: String?,
    var password: String?,
    var phone: String?,
    var photo: String?,
    @JsonAlias("birth_date")
    var birthDate: String?,
    @JsonAlias("profile_type")
    var profileType: ProfileType?
)
