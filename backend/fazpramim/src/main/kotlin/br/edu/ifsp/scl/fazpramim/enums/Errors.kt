package br.edu.ifsp.scl.fazpramim.enums

enum class Errors(val code: String, val message: String) {

    /*
    * Usado para centralizar as mensagens de erros das Exceptions
    */
    FPM101("FPM-101", "Person [%s] not exists"),
    FPM001("FPM-001", "Invalid request")

}
