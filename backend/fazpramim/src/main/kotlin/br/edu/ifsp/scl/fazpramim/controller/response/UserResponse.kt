package br.edu.ifsp.scl.fazpramim.controller.response

import br.edu.ifsp.scl.fazpramim.enums.ProfileType

data class UserResponse (
    var id: Long? = null,
    var fullName: String,
    var username: String,
    var password: String,
    var phone: String,
    var photo: String,
    var birthDate: String,
    var profileType: ProfileType
)
