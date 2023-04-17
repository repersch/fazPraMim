package br.edu.ifsp.scl.fazpramim.exception

class InvalidServiceStatusException(override val message: String, val errorCode: String): Exception() {
}
