package br.edu.ifsp.scl.fazpramim.exception

open class AuthenticationException(override val message: String, val errorCode: String): Exception()