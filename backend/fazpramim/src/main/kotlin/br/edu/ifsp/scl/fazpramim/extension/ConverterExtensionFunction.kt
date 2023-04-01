package br.edu.ifsp.scl.fazpramim.extension

import br.edu.ifsp.scl.fazpramim.controller.request.PostUserRequest
import br.edu.ifsp.scl.fazpramim.controller.request.PutPersonRequest
import br.edu.ifsp.scl.fazpramim.controller.response.UserResponse
import br.edu.ifsp.scl.fazpramim.enums.ProfileType
import br.edu.ifsp.scl.fazpramim.model.UserModel

fun PostUserRequest.toUserModel(): UserModel {
    return UserModel(
        fullName = this.fullName,
        userName = this.username,
        password = this.password,
        phone = this.phone,
        birthDate = this.birthDate,
        photo = this.photo,
        profileType = ProfileType.CLIENTE,
        accountNonExpired = true,
        accountNonLocked = true,
        enabled = true,
        credentialsNonExpired = true,
    )
}

fun PutPersonRequest.toUserModel(previuosValue: UserModel): UserModel {
    return UserModel (
        id = previuosValue.id,
        fullName = this.fullName ?: previuosValue.fullName,
        userName = this.username ?: previuosValue.userName,
        password = this.password ?: previuosValue.password,
        phone = this.phone ?: previuosValue.phone,
        birthDate = previuosValue.birthDate,
        photo = this.photo ?: previuosValue.photo
    )
}

fun UserModel.toResponse(): UserResponse {
    return UserResponse(
        id = this.id,
        fullName = this.fullName!!,
        username = this.userName!!,
        password = this.password!!,
        phone = this.phone!!,
        photo = this.photo!!,
        birthDate = this.birthDate!!,
        profileType = this.profileType
    )
}
