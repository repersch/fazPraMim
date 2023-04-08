package br.edu.ifsp.scl.fazpramim.service

import br.edu.ifsp.scl.fazpramim.controller.request.PostProfileRequest
import br.edu.ifsp.scl.fazpramim.enums.Errors
import br.edu.ifsp.scl.fazpramim.enums.ProfileType
import br.edu.ifsp.scl.fazpramim.exception.NotFoundException
import br.edu.ifsp.scl.fazpramim.model.ProfileModel
import br.edu.ifsp.scl.fazpramim.repository.ProfileRepository
import org.springframework.stereotype.Service

@Service
class ProfileService(
    val profileRepository: ProfileRepository,
    val userService: UserService
) {

    fun findAllProfiles(): List<ProfileModel> {
        return profileRepository.findAll().toList()
    }

    fun findProfileById(id: Long): ProfileModel {
        return profileRepository.findById(id)
            .orElseThrow{ NotFoundException(Errors.FPM201.message.format(id), Errors.FPM201.code) }
    }

    fun createProfile(profile: PostProfileRequest): ProfileModel {
        val user = userService.findUserById(profile.userId)
        if (user.profileType != ProfileType.PRESTADOR) userService.updateProfileTypeToPrestador(user.id)

        val profileModel = ProfileModel(
            description = profile.description,
            city = profile.city,
            user = user
        )

        val entity = profileRepository.save(profileModel)
        return findProfileById(entity.id!!)
    }

    fun updateProfile(profile: ProfileModel): ProfileModel {
        val entity = findProfileById(profile.id!!)
        entity.description = profile.description
        entity.city = profile.city
        entity.user = profile.user
        profileRepository.save(entity)
        return findProfileById(entity.id!!)
    }

    fun deleteProfile(id: Long) {
        val entity = findProfileById(id)
        userService.updateProfileTypeToCliente(entity.user.id)
        profileRepository.delete(entity)
    }

}
