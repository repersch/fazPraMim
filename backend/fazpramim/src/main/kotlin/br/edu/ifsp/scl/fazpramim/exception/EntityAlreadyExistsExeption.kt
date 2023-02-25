package br.edu.ifsp.scl.fazpramim.exception

class EntityAlreadyExistsExeption(override val message: String, val errorCode: String): Exception() {
}