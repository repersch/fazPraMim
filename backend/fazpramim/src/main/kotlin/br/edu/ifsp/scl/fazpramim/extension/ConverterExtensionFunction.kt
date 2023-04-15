package br.edu.ifsp.scl.fazpramim.extension

import br.edu.ifsp.scl.fazpramim.controller.request.*
import br.edu.ifsp.scl.fazpramim.controller.response.ProfileResponse
import br.edu.ifsp.scl.fazpramim.controller.response.ServiceResponse
import br.edu.ifsp.scl.fazpramim.controller.response.UserResponse
import br.edu.ifsp.scl.fazpramim.enums.ProfileType
import br.edu.ifsp.scl.fazpramim.model.ProfileModel
import br.edu.ifsp.scl.fazpramim.model.ServiceModel
import br.edu.ifsp.scl.fazpramim.model.UserModel
import br.edu.ifsp.scl.fazpramim.service.ServiceTypeService
import br.edu.ifsp.scl.fazpramim.service.UserService
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun PostUserRequest.toUserModel(): UserModel {
    return UserModel(
        fullName = this.fullName,
        userName = this.username,
        password = this.password,
        phone = this.phone,
        birthDate = this.birthDate,
        photo = this.photo,
        profileType = this.profileType ?: ProfileType.CLIENTE,
        accountNonExpired = true,
        accountNonLocked = true,
        enabled = true,
        credentialsNonExpired = true,
    )
}

fun PutUserRequest.toUserModel(previuosValue: UserModel): UserModel {
    return UserModel (
        id = previuosValue.id,
        fullName = this.fullName ?: previuosValue.fullName,
        userName = this.username ?: previuosValue.userName,
        password = this.password ?: previuosValue.password,
        phone = this.phone ?: previuosValue.phone,
        birthDate = previuosValue.birthDate,
        photo = this.photo ?: previuosValue.photo,
        profileType = this.profileType ?: previuosValue.profileType
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

fun PostProfileRequest.toModel(): ProfileModel {
    val userService = UserService()
    val serviceTypeService = ServiceTypeService()

    val user = userService.findUserById(this.userId)
    return ProfileModel(
        description = this.description,
        city = this.city,
        user = user,
        serviceType = serviceTypeService.findServiceTypeById(this.serviceTypeId)
    )
}

fun ProfileModel.toResponse(): ProfileResponse {
    return ProfileResponse(
        id = this.id,
        description = this.description,
        city = this.city,
        user = this.user.toResponse(),
        serviceType = this.serviceType
    )
}

fun PutProfileRequest.toProfileModel(previuosValue: ProfileModel): ProfileModel {
    return ProfileModel (
        id = previuosValue.id,
        description = this.description ?: previuosValue.description,
        city = this.city ?: previuosValue.city,
        user = this.user ?: previuosValue.user,
        serviceType = this.serviceType ?: previuosValue.serviceType
    )
}

fun ServiceModel.toResponse(): ServiceResponse {
    return ServiceResponse(
        id = this.id!!,
        date = this.date,
        status = this.status,
        client = this.client.toResponse(),
        provider = this.provider.toResponse()
    )
}

fun PutServiceRequest.toServiceModel(previousValue: ServiceModel): ServiceModel {
    return ServiceModel(
        id = previousValue.id,
        date = if (this.date != null) {
            LocalDate.parse(this.date, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        } else {
            previousValue.date
        },
        status = previousValue.status,
        client = previousValue.client,
        provider = previousValue.provider
    )
}



