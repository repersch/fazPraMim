package br.edu.ifsp.scl.fazpramim.service

import br.edu.ifsp.scl.fazpramim.enums.Errors
import br.edu.ifsp.scl.fazpramim.enums.Profile
import br.edu.ifsp.scl.fazpramim.exception.EntityAlreadyExistsExeption
import br.edu.ifsp.scl.fazpramim.exception.NotFoundException
import br.edu.ifsp.scl.fazpramim.model.PersonModel
import br.edu.ifsp.scl.fazpramim.repository.PersonRepository
import org.springframework.stereotype.Service

@Service
class PersonService(
    val repository: PersonRepository
) {

    fun findAllPersons(name: String?): List<PersonModel> {
        name?.let { return repository.findByNameContainingIgnoreCase(name) }
        return repository.findAll().toList()
    }

    fun findPersonById(id: Int): PersonModel {
        return repository.findById(id)
            .orElseThrow{ NotFoundException(Errors.FPM101.message.format(id), Errors.FPM101.code) }
    }

    fun createPerson(person: PersonModel): PersonModel {
        val personCopy = person.copy(
            roles = setOf(Profile.CLIENTE)
        )
        val entity = repository.save(personCopy)
        return findPersonById(entity.id!!)
    }

    fun updatePerson(person: PersonModel): PersonModel {
        val entity = findPersonById(person.id!!)
        entity.name = person.name
        entity.email = person.email
        entity.password = person.password
        entity.phone = person.phone
        repository.save(entity)
        return findPersonById(entity.id!!)
    }

    fun deletePerson(id: Int) {
        val entity = findPersonById(id)
        repository.delete(entity)
    }

    fun emailAvailable(email: String): Boolean {
       return !repository.existsByEmail(email)
    }
}
