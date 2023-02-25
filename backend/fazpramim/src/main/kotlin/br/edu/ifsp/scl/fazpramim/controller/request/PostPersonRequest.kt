package br.edu.ifsp.scl.fazpramim.controller.request

import com.fasterxml.jackson.annotation.JsonAlias

data class PostPersonRequest (
    var name: String,

    var email: String,

    var password: String,

    var phone: String,

    @JsonAlias("birth_date")
    var birthDate: String,
)
