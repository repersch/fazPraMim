package br.edu.ifsp.scl.fazpramim.controller.request

import br.edu.ifsp.scl.fazpramim.enums.PersonStatus
import com.fasterxml.jackson.annotation.JsonAlias

data class PutPersonRequest (
    var name: String?,

    var email: String?,

    var password: String?,

    var phone: String?,

    @JsonAlias("person_status")
    var personStatus: PersonStatus?
)
