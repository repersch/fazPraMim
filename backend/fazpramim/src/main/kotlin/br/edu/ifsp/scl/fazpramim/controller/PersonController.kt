package br.edu.ifsp.scl.fazpramim.controller

import br.edu.ifsp.scl.fazpramim.model.Person
import br.edu.ifsp.scl.fazpramim.service.PersonService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/person")
class PersonController (
    val service: PersonService
) {

    @GetMapping
    fun findAllUsers(): List<Person> {
        return service.findAllPersons()
    }

    @GetMapping("/{id}")
    fun findUserById(@PathVariable(value = "id") id: Long): Person {
        return service.findPersonById(id)
    }

    @PostMapping
    fun createUser(@RequestBody person: Person): Person {
        return service.createPerson(person)
    }

    @PutMapping
    fun updateUser(@RequestBody person: Person): Person {
        return service.updateUser(person)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable(value = "id") id: Long): ResponseEntity<*> {
        service.deletePerson(id)
        return ResponseEntity.noContent().build<Any>()
    }
}
