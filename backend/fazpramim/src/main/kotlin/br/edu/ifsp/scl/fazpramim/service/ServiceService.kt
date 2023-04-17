package br.edu.ifsp.scl.fazpramim.service

import br.edu.ifsp.scl.fazpramim.controller.request.PostServiceRequest
import br.edu.ifsp.scl.fazpramim.controller.request.PutServiceRequest
import br.edu.ifsp.scl.fazpramim.enums.Errors
import br.edu.ifsp.scl.fazpramim.enums.ServiceStatus
import br.edu.ifsp.scl.fazpramim.exception.InvalidDateException
import br.edu.ifsp.scl.fazpramim.exception.NotFoundException
import br.edu.ifsp.scl.fazpramim.model.ServiceModel
import br.edu.ifsp.scl.fazpramim.repository.ServiceRepository
import org.springframework.stereotype.Service
import java.text.DateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.logging.Logger
import javax.swing.text.DateFormatter

@Service
class ServiceService(
    var repository: ServiceRepository,
    var userService: UserService,
    var profileService: ProfileService
) {

    private val logger = Logger.getLogger(ServiceService::class.toString())

    fun findAllServices(): List<ServiceModel> {
        return repository.findAll().toList()
    }

    fun findServiceById(id: Long): ServiceModel {
        return repository.findById(id)
            .orElseThrow { NotFoundException(Errors.FPM401.message.format(id), Errors.FPM401.code) }
    }

    // na criação do serviço não é enviado nem status (nesse momento é sempre CREATED),
    // nem rating (é definido depois que o serviço é concluído)
    fun createService(service: PostServiceRequest): ServiceModel {
        val client = userService.findUserById(service.clientId)
        val provider = profileService.findProfileById(service.providerId)
        val date = LocalDate.parse(service.date, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        if (date <= LocalDate.now()) throw InvalidDateException(Errors.FPM501.message, Errors.FPM501.code)

        val entity = repository.save(
            ServiceModel (
                client = client,
                provider = provider,
                date = date,
                hour = service.hour,
                requestDetails = service.requestDetails,
                status = ServiceStatus.CREATED
            )
        )
        return findServiceById(entity.id!!)
    }

    fun updateService(service: ServiceModel): ServiceModel {
        val entity = findServiceById(service.id!!)
        if (service.date <= LocalDate.now()) throw InvalidDateException(Errors.FPM501.message, Errors.FPM501.code)
        entity.date = service.date
        entity.hour = service.hour
        entity.requestDetails = service.requestDetails
        repository.save(entity)
        return findServiceById(entity.id!!)
    }

    // TODO: pensar numa forma de atualizar o status com um método só

    // TODO: pensar em uma forma de o cliente avaliar o serviço

    fun deleteService(id: Long) {
        val entity = findServiceById(id)
        repository.delete(entity)
    }

}