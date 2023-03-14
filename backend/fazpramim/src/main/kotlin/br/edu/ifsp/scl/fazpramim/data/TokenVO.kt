package br.edu.ifsp.scl.fazpramim.data

import java.util.*

data class TokenVO(
    val username: String? = null,
    val authenticated: Boolean? = null,
    val created: Date? = null,
    val expiration: Date? = null,
    val accessToken: String? = null,
    val refreshToken: String? = null
)
