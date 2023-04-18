package br.edu.ifsp.scl.fazpramim.service

import br.edu.ifsp.scl.fazpramim.controller.request.PostServiceRequest
import br.edu.ifsp.scl.fazpramim.enums.Errors
import br.edu.ifsp.scl.fazpramim.enums.ServiceStatus
import br.edu.ifsp.scl.fazpramim.exception.*
import br.edu.ifsp.scl.fazpramim.model.ServiceModel
import br.edu.ifsp.scl.fazpramim.repository.ServiceRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.logging.Logger

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

    fun findServiceByClient(clientId: Long): List<ServiceModel> {
        val servicesByClient = repository.findServiceByClientId(clientId)
        if (servicesByClient.isEmpty())
            throw EmptyServiceListException(Errors.FPM801.message.format(clientId), Errors.FPM801.code)
        return servicesByClient
    }

    fun findServiceByProvider(providerId: Long): List<ServiceModel> {
        val servicesByProvider = repository.findServiceByProviderId(providerId)
        if (servicesByProvider.isEmpty())
            throw EmptyServiceListException(Errors.FPM802.message.format(providerId), Errors.FPM802.code)
        return servicesByProvider
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

    fun updateServiceStatus(serviceId: Long, status: Int): ServiceModel {
        val service = findServiceById(serviceId)
        if (service.status == ServiceStatus.CANCELED || service.status == ServiceStatus.FINISHED)
            throw InvalidServiceStatusException(Errors.FPM601.message, Errors.FPM601.code)

        when (status) {
            1 -> service.status = ServiceStatus.CREATED
            2 -> service.status = ServiceStatus.ACCEPTED
            3 -> service.status = ServiceStatus.FINISHED
            4 -> service.status = ServiceStatus.CANCELED
        }
        return repository.save(service)
    }

    fun evaluateService(serviceId: Long, rating: Int): ServiceModel {
        val service = findServiceById(serviceId)
        if (service.status != ServiceStatus.FINISHED)
            throw InvalidEvaluationException(Errors.FPM701.message, Errors.FPM701.code)

        service.rating = rating
        return repository.save(service)
    }


    fun deleteService(id: Long) {
        val entity = findServiceById(id)
        repository.delete(entity)
    }

}