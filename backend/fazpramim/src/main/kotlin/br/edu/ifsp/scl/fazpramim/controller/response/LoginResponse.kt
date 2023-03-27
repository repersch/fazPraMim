package br.edu.ifsp.scl.fazpramim.controller.response

import java.util.Date

data class LoginResponse(
    val email: String? = null,
    val id: Long? = null,
    val authenticated: Boolean? = null,
    val created: Date? = null,
    val expiration: Date? = null,
    val accessToken: String? = null,
    val refreshToken: String? = null
)
