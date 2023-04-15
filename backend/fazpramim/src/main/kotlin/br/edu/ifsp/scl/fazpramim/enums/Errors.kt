package br.edu.ifsp.scl.fazpramim.enums

enum class Errors(val code: String, val message: String) {

    /*
    * Usado para centralizar as mensagens de erros das Exceptions
    */
    FPM101("FPM-101", "Não existe pessoa cadastrada com o ID: %s."),
    FPM102("FPM-102", "E-mail já cadastrado."),
    FPM103("FPM-103", "Token inválido."),
    FPM001("FPM-001", "Requisição inválida."),

    FPM201("FPM-201", "Não existe perfil cadastrada com o ID: %s."),
    FPM301("FPM-301", "Não existe tipo de serviço cadastrada com o ID: %s."),

    FPM401("FPM-401", "Não existe serviço cadastrada com o ID: %s."),

}
