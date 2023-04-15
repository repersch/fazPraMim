package br.edu.ifsp.scl.fazpramim.exception

class InvalidDateException(override val message: String, val errorCode: String): Exception() {
}