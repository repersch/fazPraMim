package br.edu.ifsp.scl.fazpramim.controller

import br.edu.ifsp.scl.fazpramim.controller.request.PostPersonRequest
import br.edu.ifsp.scl.fazpramim.controller.request.PutPersonRequest
import br.edu.ifsp.scl.fazpramim.controller.response.PersonResponse
import br.edu.ifsp.scl.fazpramim.extension.toPersonModel
import br.edu.ifsp.scl.fazpramim.extension.toResponse
import br.edu.ifsp.scl.fazpramim.service.PersonService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/person")
@Tag(name = "People", description = "Endpoints para gerenciar pessoas")
class PersonController (
    val service: PersonService
) {

    @GetMapping
    @Operation(summary = "Busca todas as pessoas", description = "Busca todas as pessoas")
    fun findAllPersons(@RequestParam name: String?,
                       @RequestHeader("Authorization") token: String?): List<PersonResponse> {
        return service.findAllPersons(name).map { it.toResponse() }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma pessoa por ID", description = "Busca uma pessoa por ID")
    fun findPersonById(@PathVariable(value = "id") id: Int): PersonResponse {
        return service.findPersonById(id).toResponse()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria uma pessoa", description = "Cria uma pessoa")
    fun createPerson(@RequestBody @Valid person: PostPersonRequest): PersonResponse {
        return service.createPerson(person.toPersonModel()).toResponse()
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edita uma pessoa", description = "Edita uma pessoa")
    fun updatePerson(@PathVariable id: Int, @RequestBody @Valid person: PutPersonRequest): PersonResponse {
        val personSaved = service.findPersonById(id)
        return service.updatePerson(person.toPersonModel(personSaved)).toResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Exclui uma pessoa", description = "Exclui uma pessoa")
    fun deletePerson(@PathVariable(value = "id") id: Int) {
        service.deletePerson(id)
    }
}
