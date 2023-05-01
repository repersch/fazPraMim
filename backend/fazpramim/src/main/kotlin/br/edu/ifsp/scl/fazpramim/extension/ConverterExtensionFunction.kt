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
import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun PostUserRequest.toUserModel(): UserModel {
    return UserModel(
        fullName = this.fullName,
        userName = this.username,
        password = passwordEncoder().encode(this.password),
        phone = this.phone,
        birthDate = LocalDate.parse(this.birthDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
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
        password = if (this.password != null) passwordEncoder().encode(this.password) else previuosValue.password,
        phone = this.phone ?: previuosValue.phone,
        birthDate = if (this.birthDate != null) LocalDate.parse(this.birthDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            else LocalDate.parse(previuosValue.birthDate.toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
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
        serviceType = this.serviceType,
        rating = this.rating
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
        hour = this.hour,
        requestDetails = this.requestDetails,
        rating = this.rating,
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
        hour = this.hour ?: previousValue.hour,
        requestDetails = this.requestDetails ?: previousValue.requestDetails,
        status = previousValue.status,
        client = previousValue.client,
        provider = previousValue.provider
    )
}

private fun passwordEncoder() : PasswordEncoder {
    val encoders: MutableMap<String, PasswordEncoder> = HashMap<String, PasswordEncoder>()
    val pbkdf2Encoder = Pbkdf2PasswordEncoder("", 8, 185000, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256)
    encoders["pbkdf2"] = pbkdf2Encoder
    val passwordEncoder = DelegatingPasswordEncoder("pbkdf2", encoders)
    passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder)
    return passwordEncoder
}



