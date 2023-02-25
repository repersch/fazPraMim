package br.edu.ifsp.scl.fazpramim.controller.response

import br.edu.ifsp.scl.fazpramim.enums.PersonStatus

data class PersonResponse (
    var id: Int? = null,

    var name: String,

    var email: String,

    var password: String,

    var phone: String,

    var birthDate: String,

    var personStatus: PersonStatus?
)
