package br.edu.ifsp.scl.fazpramim.controller

import br.edu.ifsp.scl.fazpramim.controller.request.PostServiceRequest
import br.edu.ifsp.scl.fazpramim.controller.request.PutServiceRequest
import br.edu.ifsp.scl.fazpramim.controller.response.ServiceResponse
import br.edu.ifsp.scl.fazpramim.extension.toResponse
import br.edu.ifsp.scl.fazpramim.extension.toServiceModel
import br.edu.ifsp.scl.fazpramim.service.ServiceService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/services")
@Tag(name = "Service", description = "Endpoints para gerenciar serviços")
class ServiceController (
    val serviceClass: ServiceService
) {

    @GetMapping
    @Operation(summary = "Busca todos os serviços", description = "Busca todos os serviços")
    fun findAllServices(): List<ServiceResponse> {
        return serviceClass.findAllServices().map { service -> service.toResponse() }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um serviço pelo ID", description = "Busca um serviço pelo ID")
    fun findServiceById(@PathVariable(value = "id") id: Long): ServiceResponse {
        return serviceClass.findServiceById(id).toResponse()
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Busca todos os serviço vinculados ao cliente", description = "Busca todos os serviços vinculados ao ID do cliente")
    fun findServiceByClient(@PathVariable clientId: Long): List<ServiceResponse> {
        return serviceClass.findServiceByClient(clientId).map { s -> s.toResponse() }
    }

    @GetMapping("/provider/{userId}")
    @Operation(summary = "Busca todos os serviço vinculados ao prestador", description = "Busca todos os serviços vinculados ao userID do prestador")
    fun findServiceByUserId(@PathVariable userId: Long): List<ServiceResponse> {
        return serviceClass.findServiceByProvider(userId).map { s -> s.toResponse() }
    }

    @PostMapping
    @Operation(summary = "Cria um serviço", description = "Cria um serviço")
    fun createService(@RequestBody service: PostServiceRequest): ServiceResponse {
        return serviceClass.createService(service).toResponse()
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edita um serviço", description = "Edita um serviço")
    fun updateService(@PathVariable id: Long, @RequestBody service: PutServiceRequest): ServiceResponse {
        val serviceSaved = serviceClass.findServiceById(id)
        return serviceClass.updateService(service.toServiceModel(serviceSaved)).toResponse()
    }

    @PutMapping("/{id}/{status}")
    @Operation(summary = "Atualiza o status de um serviço", description = "Atualiza o status de um serviço")
    fun updateServiceStatus(@PathVariable id: Long, @PathVariable status: Int): ServiceResponse {
        return serviceClass.updateServiceStatus(id, status).toResponse()
    }

    @PutMapping("/evaluate/{id}")
    @Operation(summary = "Avalia um serviço", description = "Avalia um serviço")
    fun evaluateService(@PathVariable id: Long, @RequestBody rating: Int): ServiceResponse {
        return serviceClass.evaluateService(id, rating).toResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Exclui um serviço", description = "Exclui um serviço")
    fun deleteUser(@PathVariable(value = "id") id: Long) {
        serviceClass.deleteService(id)
    }
}
