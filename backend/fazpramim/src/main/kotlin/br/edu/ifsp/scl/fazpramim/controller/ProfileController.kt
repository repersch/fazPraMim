package br.edu.ifsp.scl.fazpramim.controller

import br.edu.ifsp.scl.fazpramim.model.ProfileModel
import br.edu.ifsp.scl.fazpramim.service.ProfileService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/profiles")
@Tag(name = "People", description = "Endpoints para gerenciar perfis")
class ProfileController (
    val service: ProfileService
) {

    @GetMapping
    @Operation(summary = "Busca todas os perfis", description = "Busca todas os perfis")
    fun findAllPersons(@RequestParam name: String?,
                       @RequestHeader("Authorization") token: String?): List<ProfileModel> {
        return service.findAllProfiles()
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um perfil por ID", description = "Busca um perfil por ID")
    fun findPersonById(@PathVariable(value = "id") id: Int): ProfileModel {
        return service.findProfileById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria um perfil", description = "Cria um perfil")
    fun createPerson(@RequestBody profile: ProfileModel): ProfileModel {
        return service.createProfile(profile)
    }

//    @PutMapping("/{id}")
//    @Operation(summary = "Edita uma pessoa", description = "Edita uma pessoa")
//    fun updatePerson(@PathVariable id: Int, @RequestBody @Valid person: PutPersonRequest): PersonResponse {
//        val personSaved = service.findPersonById(id)
//        return service.updatePerson(person.toPersonModel(personSaved)).toResponse()
//    }
//
//    @PutMapping("/setPrestador/{id}")
//    @Operation(summary = "Edita o tipo de perfil de uma pessoa", description = "Edita o tipo de perfil de uma pessoa")
//    fun updateProfileType(@PathVariable id: Int): PersonResponse {
//        return service.updateProfileTypeToPrestador(id).toResponse()
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @Operation(summary = "Exclui uma pessoa", description = "Exclui uma pessoa")
//    fun deletePerson(@PathVariable(value = "id") id: Int) {
//        service.deletePerson(id)
//    }
}
