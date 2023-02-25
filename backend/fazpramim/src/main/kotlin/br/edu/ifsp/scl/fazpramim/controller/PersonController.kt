package br.edu.ifsp.scl.fazpramim.controller

import br.edu.ifsp.scl.fazpramim.controller.request.PostPersonRequest
import br.edu.ifsp.scl.fazpramim.controller.request.PutPersonRequest
import br.edu.ifsp.scl.fazpramim.extension.toPersonModel
import br.edu.ifsp.scl.fazpramim.model.PersonModel
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
    fun findAllPersons(@RequestParam name: String?): List<PersonModel> {
        return service.findAllPersons(name)
    }

    @GetMapping("/{id}")
    fun findPersonById(@PathVariable(value = "id") id: Int): PersonModel {
        return service.findPersonById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPerson(@RequestBody @Valid person: PostPersonRequest): PersonModel {
        return service.createPerson(person.toPersonModel())
    }

    @PutMapping("/{id}")
    fun updatePerson(@PathVariable id: Int, @RequestBody @Valid person: PutPersonRequest): PersonModel {
        val personSaved = service.findPersonById(id)
        return service.updatePerson(person.toPersonModel(personSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePerson(@PathVariable(value = "id") id: Int) {
        service.deletePerson(id)
    }
}
