package br.edu.ifsp.scl.fazpramim.controller.request

import com.fasterxml.jackson.annotation.JsonAlias

data class PostProfileRequest (
   var description: String,
   var city: String,
   @JsonAlias("user_id")
   var userId: Long,
   @JsonAlias("service_type_id")
   var serviceTypeId: Long
)
