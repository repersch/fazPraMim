package br.edu.ifsp.scl.fazpramim.controller.request

import com.fasterxml.jackson.annotation.JsonAlias

data class PutServiceRequest (
    var id: Long,
    var date: String?,
    var hour: String?,

    @JsonAlias("request_details")
    var requestDetails: String?
)
