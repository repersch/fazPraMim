package br.edu.ifsp.scl.fazpramim.exception

class NotFoundException(override val message: String, val errorCode: String): Exception() {
}