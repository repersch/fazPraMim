package br.edu.ifsp.scl.fazpramim.service

import br.edu.ifsp.scl.fazpramim.enums.Errors
import br.edu.ifsp.scl.fazpramim.exception.NotFoundException
import br.edu.ifsp.scl.fazpramim.model.ProfileModel
import br.edu.ifsp.scl.fazpramim.repository.ProfileRepository
import org.springframework.stereotype.Service

@Service
class ProfileService(
    val repository: ProfileRepository
) {

    fun findAllProfiles(): List<ProfileModel> {
        return repository.findAll().toList()
    }

    fun findProfileById(id: Int): ProfileModel {
        return repository.findById(id)
            .orElseThrow{ NotFoundException(Errors.FPM201.message.format(id), Errors.FPM201.code) }
    }

    fun createProfile(profile: ProfileModel): ProfileModel {
        val entity = repository.save(profile)
        return findProfileById(entity.id!!)
    }
}
