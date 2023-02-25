package br.edu.ifsp.scl.fazpramim.controller.request

import br.edu.ifsp.scl.fazpramim.enums.PersonStatus

data class PutPersonRequest (
    var name: String?,

    var email: String?,

    var password: String?,

    var phone: String?,

    var personStatus: PersonStatus?
)
