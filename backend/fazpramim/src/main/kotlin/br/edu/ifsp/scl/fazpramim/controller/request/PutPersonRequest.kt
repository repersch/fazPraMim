package br.edu.ifsp.scl.fazpramim.controller.request

import com.fasterxml.jackson.annotation.JsonAlias

data class PutPersonRequest (
    var id: Long?,
    var fullName: String?,
    var username: String?,
    var password: String?,
    var phone: String?,
    var photo: String?,
    @JsonAlias("birth_date")
    var birthDate: String?
)
