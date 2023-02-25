package br.edu.ifsp.scl.fazpramim.service

import br.edu.ifsp.scl.fazpramim.enums.Errors
import br.edu.ifsp.scl.fazpramim.exception.NotFoundException
import br.edu.ifsp.scl.fazpramim.model.Person
import br.edu.ifsp.scl.fazpramim.repository.PersonRepository
import org.springframework.stereotype.Service

@Service
class PersonService(
    val repository: PersonRepository
) {

    fun findAllPersons(name: String?): List<Person> {
        name?.let { return repository.findByNameContainingIgnoreCase(name) }
        return repository.findAll().toList()
    }

    fun findPersonById(id: Int): Person {
        return repository.findById(id)
            .orElseThrow{ NotFoundException(Errors.FPM101.message.format(id), Errors.FPM101.code) }
    }

    fun createPerson(person: Person): Person {
        val entity = repository.save(person)
        return findPersonById(entity.id!!)
    }

    fun updatePerson(id: Int, person: Person): Person {
        val entity = findPersonById(id)
        entity.name = person.name
        entity.email = person.email
        entity.password = person.password
        entity.phone = person.phone

        return repository.save(entity)
    }

    fun deletePerson(id: Int) {
        val entity = findPersonById(id)
        repository.delete(entity)
    }

    fun emailAvailable(email: String): Boolean {
       return !repository.existsByEmail(email)
    }
}
