package br.edu.ifsp.scl.fazpramim.service

import br.edu.ifsp.scl.fazpramim.controller.request.PostServiceRequest
import br.edu.ifsp.scl.fazpramim.controller.request.PostUserRequest
import br.edu.ifsp.scl.fazpramim.enums.Errors
import br.edu.ifsp.scl.fazpramim.exception.EntityAlreadyExistsExeption
import br.edu.ifsp.scl.fazpramim.exception.NotFoundException
import br.edu.ifsp.scl.fazpramim.extension.toUserModel
import br.edu.ifsp.scl.fazpramim.model.ServiceModel
import br.edu.ifsp.scl.fazpramim.model.UserModel
import br.edu.ifsp.scl.fazpramim.repository.ServiceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class ServiceService {

    @Autowired
    private lateinit var repository: ServiceRepository

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var profileService: ProfileService

    private val logger = Logger.getLogger(ServiceService::class.toString())

    fun findAllServices(): List<ServiceModel> {
        return repository.findAll().toList()
    }

    fun findServiceById(id: Long): ServiceModel {
        return repository.findById(id)
            .orElseThrow { NotFoundException(Errors.FPM401.message.format(id), Errors.FPM401.code) }
    }

    fun createService(service: PostServiceRequest): ServiceModel {
        val client = userService.findUserById(service.clientId)
        val provider = profileService.findProfileById(service.providerId)
        val entity = repository.save(ServiceModel(client = client, provider = provider))
        return findServiceById(entity.id!!)
    }

//    fun updateUser(user: UserModel): ServiceModel {
//        val entity = findUserById(user.id)
//        entity.fullName = user.fullName
//        entity.userName = user.username
//        entity.password = passwordEncoder().encode(user.password)
//        entity.phone = user.phone
//        entity.photo = user.photo
//        entity.birthDate = user.birthDate
//        repository.save(entity)
//        return findUserById(entity.id)
//    }
//
//    fun deleteUser(id: Long) {
//        val entity = findUserById(id)
//        repository.delete(entity)
//    }
//
}