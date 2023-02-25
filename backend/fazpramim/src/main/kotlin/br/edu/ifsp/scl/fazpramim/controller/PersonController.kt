package br.edu.ifsp.scl.fazpramim.controller

import br.edu.ifsp.scl.fazpramim.controller.request.PostPersonRequest
import br.edu.ifsp.scl.fazpramim.controller.request.PutPersonRequest
import br.edu.ifsp.scl.fazpramim.controller.response.PersonResponse
import br.edu.ifsp.scl.fazpramim.extension.toPersonModel
import br.edu.ifsp.scl.fazpramim.extension.toResponse
import br.edu.ifsp.scl.fazpramim.service.PersonService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/person")
class PersonController (
    val service: PersonService
) {

    @GetMapping
    fun findAllPersons(@RequestParam name: String?): List<PersonResponse> {
        return service.findAllPersons(name).map { it.toResponse() }
    }

    @GetMapping("/{id}")
    fun findPersonById(@PathVariable(value = "id") id: Int): PersonResponse {
        return service.findPersonById(id).toResponse()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPerson(@RequestBody @Valid person: PostPersonRequest): PersonResponse {
        return service.createPerson(person.toPersonModel()).toResponse()
    }

    @PutMapping("/{id}")
    fun updatePerson(@PathVariable id: Int, @RequestBody @Valid person: PutPersonRequest): PersonResponse {
        val personSaved = service.findPersonById(id)
        return service.updatePerson(person.toPersonModel(personSaved)).toResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePerson(@PathVariable(value = "id") id: Int) {
        service.deletePerson(id)
    }
}
