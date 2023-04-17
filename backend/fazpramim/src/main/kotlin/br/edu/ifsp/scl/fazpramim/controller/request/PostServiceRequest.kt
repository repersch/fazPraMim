package br.edu.ifsp.scl.fazpramim.controller.request

import com.fasterxml.jackson.annotation.JsonAlias

data class PostServiceRequest (

   var date: String,

   var hour: String,

   @JsonAlias("request_details")
   var requestDetails: String,

   @JsonAlias("client_id")
   var clientId: Long,

   @JsonAlias("provider_id")
   var providerId: Long
)
