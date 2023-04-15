package br.edu.ifsp.scl.fazpramim.controller

import br.edu.ifsp.scl.fazpramim.controller.request.PostServiceRequest
import br.edu.ifsp.scl.fazpramim.model.ServiceModel
import br.edu.ifsp.scl.fazpramim.service.ServiceService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/services")
@Tag(name = "Service", description = "Endpoints para gerenciar serviços")
class ServiceController (
    val serviceClass: ServiceService
) {

    @GetMapping
    @Operation(summary = "Busca todos os serviços", description = "Busca todos os serviços")
    fun findAllServices(): List<ServiceModel> {
        return serviceClass.findAllServices()
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um serviço pelo ID", description = "Busca um serviço pelo ID")
    fun findServiceById(@PathVariable(value = "id") id: Long): ServiceModel {
        return serviceClass.findServiceById(id)
    }

    @PostMapping
    @Operation(summary = "Cria um serviço", description = "Cria um serviço")
    fun createService(@RequestBody service: PostServiceRequest): ServiceModel {
        return serviceClass.createService(service)
    }

//    @PutMapping("/{id}")
//    @Operation(summary = "Edita uma pessoa", description = "Edita uma pessoa")
//    fun updateUser(@PathVariable id: Long, @RequestBody user: PutUserRequest): UserResponse {
//        val userSaved = service.findUserById(id)
//        return service.updateUser(user.toUserModel(userSaved)).toResponse()
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @Operation(summary = "Exclui uma pessoa", description = "Exclui uma pessoa")
//    fun deleteUser(@PathVariable(value = "id") id: Long) {
//        service.deleteUser(id)
//    }
}
