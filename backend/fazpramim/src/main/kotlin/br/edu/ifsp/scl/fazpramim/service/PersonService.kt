package br.edu.ifsp.scl.fazpramim.service

import br.edu.ifsp.scl.fazpramim.exception.ResourceNotFoundException
import br.edu.ifsp.scl.fazpramim.model.Person
import br.edu.ifsp.scl.fazpramim.repository.PersonRepository
import org.springframework.stereotype.Service

@Service
class PersonService(
    val repository: PersonRepository
) {

    fun findAllPersons(): List<Person> {
        return repository.findAll()
    }

    fun findPersonById(id: Long): Person {
        return repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No Person found for this ID!") }
    }

    fun createPerson(person: Person): Person {
        val entity = repository.save(person)
        return findPersonById(entity.id)
    }

    fun updateUser(person: Person): Person {
        val entity = findPersonById(person.id)
        entity.name = person.name
        entity.email = person.email
        entity.password = person.password
        entity.phone = person.phone
        entity.birthDate = person.birthDate

        return repository.save(entity)
    }

    fun deletePerson(id: Long) {
        val entity = findPersonById(id)
        repository.delete(entity)
    }
}
