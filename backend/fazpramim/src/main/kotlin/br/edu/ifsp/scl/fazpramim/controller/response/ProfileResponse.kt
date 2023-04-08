package br.edu.ifsp.scl.fazpramim.controller.response

import br.edu.ifsp.scl.fazpramim.model.ServiceTypeModel
import br.edu.ifsp.scl.fazpramim.model.UserModel

data class ProfileResponse(
    var id: Long?,
    var description: String,
    var city: String,
    var user: UserModel,
    var serviceType: ServiceTypeModel
)

