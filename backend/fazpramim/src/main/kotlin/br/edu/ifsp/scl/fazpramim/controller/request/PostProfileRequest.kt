package br.edu.ifsp.scl.fazpramim.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import jakarta.validation.constraints.NotEmpty

data class PostProfileRequest (
   @field:NotEmpty(message = "Descrição deve ser informado.")
   var description: String,

   @field:NotEmpty(message = "Cidade deve ser informada.")
   var city: String,

   @JsonAlias("user_id")
   @field:NotEmpty(message = "Usuário deve ser informado.")
   var userId: Long,

   @JsonAlias("service_type_id")
   @field:NotEmpty(message = "Tipo de serviço deve ser informado.")
   var serviceTypeId: Long
)
