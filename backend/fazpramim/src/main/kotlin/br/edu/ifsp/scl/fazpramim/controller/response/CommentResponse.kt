package br.edu.ifsp.scl.fazpramim.controller.response

data class CommentResponse(
    var id: Long?,
    var client: UserResponse,
    var provider: ProfileResponse,
    var comment: String
)

