package br.edu.ifsp.scl.fazpramim.extension

import br.edu.ifsp.scl.fazpramim.controller.request.PostPersonRequest
import br.edu.ifsp.scl.fazpramim.controller.request.PutPersonRequest
import br.edu.ifsp.scl.fazpramim.controller.response.PersonResponse
import br.edu.ifsp.scl.fazpramim.enums.PersonStatus
import br.edu.ifsp.scl.fazpramim.enums.ProfileType
import br.edu.ifsp.scl.fazpramim.model.PersonModel

fun PostPersonRequest.toPersonModel(): PersonModel {
    return PersonModel(
        name = this.name,
        email = this.email,
        password = this.password,
        phone = this.phone,
        birthDate = this.birthDate,
        photo = this.photo,
        profileType = ProfileType.CLIENTE,
        personStatus = PersonStatus.ATIVO
    )
}

fun PutPersonRequest.toPersonModel(previuosValue: PersonModel): PersonModel {
    return PersonModel(
        id = previuosValue.id,
        name = this.name ?: previuosValue.name,
        email = this.email ?: previuosValue.email,
        password = this.password ?: previuosValue.password,
        phone = this.phone ?: previuosValue.phone,
        birthDate = previuosValue.birthDate,
        photo = this.photo ?: previuosValue.photo,
        profileType = this.profileType ?: previuosValue.profileType,
        personStatus = this.personStatus ?: previuosValue.personStatus
    )
}

fun PersonModel.toResponse(): PersonResponse {
    return PersonResponse(
        id = this.id,
        name = this.name,
        email = this.email,
        password = this.password,
        phone = this.phone,
        birthDate = this.birthDate,
        profileType = this.profileType,
        personStatus = this.personStatus
    )
}
