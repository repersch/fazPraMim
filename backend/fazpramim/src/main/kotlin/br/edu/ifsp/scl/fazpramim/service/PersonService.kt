package br.edu.ifsp.scl.fazpramim.service

import br.edu.ifsp.scl.fazpramim.exception.ResourceNotFoundException
import br.edu.ifsp.scl.fazpramim.model.Person
import br.edu.ifsp.scl.fazpramim.repository.PersonRepository
import org.springframework.stereotype.Service

@Service
class PersonService(
    val repository: PersonRepository
) {

    fun findAllPersons(name: String?): List<Person> {
        name?.let { return repository.findByNameContainingIgnoreCase(name) }
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

    fun updatePerson(id: Long, person: Person): Person {
        val entity = findPersonById(id)
        entity.name = person.name
        entity.email = person.email
        entity.password = person.password
        entity.phone = person.phone

        return repository.save(entity)
    }

    fun deletePerson(id: Long) {
        val entity = findPersonById(id)
        repository.delete(entity)
    }
}
