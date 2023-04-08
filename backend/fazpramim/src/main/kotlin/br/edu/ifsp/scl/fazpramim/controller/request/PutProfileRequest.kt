package br.edu.ifsp.scl.fazpramim.controller.request

import br.edu.ifsp.scl.fazpramim.model.UserModel

data class PutProfileRequest (
    var id: Long,
    var description: String?,
    var city: String?,
    var user: UserModel?
)
