package br.edu.ifsp.scl.fazpramim.controller.request

import com.fasterxml.jackson.annotation.JsonAlias

data class PostServiceRequest (

   @JsonAlias("client_id")
   var clientId: Long,

   @JsonAlias("provider_id")
   var providerId: Long,

   var date: String

)
