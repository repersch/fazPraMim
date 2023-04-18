package br.edu.ifsp.scl.fazpramim.exception

class EmptyServiceListException(override val message: String, val errorCode: String): Exception() {
}
