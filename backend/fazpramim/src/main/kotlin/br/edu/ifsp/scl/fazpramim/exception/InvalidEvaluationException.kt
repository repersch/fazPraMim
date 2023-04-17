package br.edu.ifsp.scl.fazpramim.exception

class InvalidEvaluationException(override val message: String, val errorCode: String): Exception() {
}