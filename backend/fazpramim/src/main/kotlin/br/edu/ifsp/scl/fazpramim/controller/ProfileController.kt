package br.edu.ifsp.scl.fazpramim.controller

import br.edu.ifsp.scl.fazpramim.controller.request.PostProfileRequest
import br.edu.ifsp.scl.fazpramim.controller.request.PutProfileRequest
import br.edu.ifsp.scl.fazpramim.controller.response.ProfileResponse
import br.edu.ifsp.scl.fazpramim.extension.toProfileModel
import br.edu.ifsp.scl.fazpramim.extension.toResponse
import br.edu.ifsp.scl.fazpramim.service.ProfileService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/profiles")
@Tag(name = "Profile", description = "Endpoints para gerenciar perfis")
class ProfileController (
    val service: ProfileService
) {

    @GetMapping
    @Operation(summary = "Busca todas os perfis", description = "Busca todas os perfis")
    fun findAllProfiles(): List<ProfileResponse> {
        return service.findAllProfiles().map { p -> p.toResponse() }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um perfil por ID", description = "Busca um perfil por ID")
    fun findProfileById(@PathVariable(value = "id") id: Long): ProfileResponse {
        return service.findProfileById(id).toResponse()
    }

    @GetMapping("/serviceDescription/{serviceProfileDescription}")
    @Operation(summary = "Busca perfis pelo tipo de serviço", description = "Busca perfis pelo tipo de serviço")
    fun findProfileBysetviceTypeDescription(@PathVariable serviceProfileDescription: String): List<ProfileResponse> {
        return service.findProfileByServiceTypeDescription(serviceProfileDescription).map { p -> p.toResponse() }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria um perfil", description = "Cria um perfil")
    fun createProfile(@RequestBody profile: PostProfileRequest): ProfileResponse {
        return service.createProfile(profile).toResponse()
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edita um perfil", description = "Edita um perfil")
    fun updateProfile(@PathVariable id: Long, @RequestBody @Valid profile: PutProfileRequest): ProfileResponse {
        val profileSaved = service.findProfileById(id)
        return service.updateProfile(profile.toProfileModel(profileSaved)).toResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Exclui um perfil", description = "Exclui um perfil")
    fun deletePerson(@PathVariable(value = "id") id: Long) {
        service.deleteProfile(id)
    }
}
