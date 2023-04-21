package br.edu.ifsp.scl.fazpramim.controller

import br.edu.ifsp.scl.fazpramim.model.ServiceTypeModel
import br.edu.ifsp.scl.fazpramim.service.ServiceTypeService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/service-type")
@Tag(name = "Service Type", description = "Endpoints para gerenciar tipos de serviços")
class ServiceTypeController(
    val serviceTypeService: ServiceTypeService
) {

    @GetMapping
    @Operation(summary = "Busca todos os tipos de serviço", description = "Busca todos os tipos de serviço")
    fun findAllServiceType(): List<ServiceTypeModel> {
        return serviceTypeService.findAllServiceType()
    }

    @GetMapping("{id}")
    @Operation(summary = "Busca um tipo de serviço por ID", description = "Busca um tipo de serviço por ID")
    fun findServiceTypeById(@PathVariable id: Long): ServiceTypeModel {
        return serviceTypeService.findServiceTypeById(id)
    }
}