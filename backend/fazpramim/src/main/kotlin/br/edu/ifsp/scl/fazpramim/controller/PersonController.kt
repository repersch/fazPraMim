package br.edu.ifsp.scl.fazpramim.controller

import br.edu.ifsp.scl.fazpramim.model.Person
import br.edu.ifsp.scl.fazpramim.service.PersonService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/person")
class PersonController (
    val service: PersonService
) {

    @GetMapping
    fun findAllUsers(@RequestParam name: String?): List<Person> {
        return service.findAllPersons(name)
    }

    @GetMapping("/{id}")
    fun findPersonById(@PathVariable(value = "id") id: Long): Person {
        return service.findPersonById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPerson(@RequestBody person: Person): Person {
        return service.createPerson(person)
    }

    @PutMapping
    fun updatePerson(@PathVariable id: Long, @RequestBody person: Person): Person {
        return service.updatePerson(id, person)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePerson(@PathVariable(value = "id") id: Long): ResponseEntity<*> {
        service.deletePerson(id)
        return ResponseEntity.noContent().build<Any>()
    }
}
