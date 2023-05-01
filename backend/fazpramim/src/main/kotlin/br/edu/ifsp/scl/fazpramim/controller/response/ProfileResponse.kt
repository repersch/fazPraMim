package br.edu.ifsp.scl.fazpramim.controller.response

import br.edu.ifsp.scl.fazpramim.model.ServiceTypeModel

data class ProfileResponse(
    var id: Long?,
    var description: String,
    var city: String,
    var user: UserResponse,
    var serviceType: ServiceTypeModel,
    var rating: Int
)

