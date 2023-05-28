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
    FPM501("FPM-501", "A data do serviço deve ser posterior ao dia de hoje."),

    FPM601("FPM-601", "Não é possível alterar o status de um serviço finalizado ou cancelado."),
    FPM701("FPM-701", "Não é possível avaliar um serviço que não foi finalizado."),

    FPM801("FPM-801", "Não existem serviços cadastrados para o cliente com ID: %s."),
    FPM802("FPM-802", "Não existem serviços cadastrados para o prestador com ID: %s."),

    FPM901("FPM-901", "Não existe comentário cadastrado com o ID: %s."),
    FPM902("FPM-902", "Não existem comentários cadastrados para o prestador com ID: %s.")
}
