package br.edu.ifsp.scl.fazpramim.service

import br.edu.ifsp.scl.fazpramim.enums.Errors
import br.edu.ifsp.scl.fazpramim.enums.ProfileType
import br.edu.ifsp.scl.fazpramim.exception.NotFoundException
import br.edu.ifsp.scl.fazpramim.model.PersonModel
import br.edu.ifsp.scl.fazpramim.repository.PersonRepository
import org.springframework.stereotype.Service

@Service
class PersonService(
    val repository: PersonRepository,
    val userService: UserService
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
        person.profileType = ProfileType.CLIENTE
        val entity = repository.save(person)
        userService.createUser(person)
        return findPersonById(entity.id!!)
    }

    fun updatePerson(person: PersonModel): PersonModel {
        val entity = findPersonById(person.id!!)
        entity.name = person.name
        entity.email = person.email
        entity.password = person.password
        entity.phone = person.phone
        entity.photo = person.photo
        repository.save(entity)
        userService.updateUser(entity)
        return findPersonById(entity.id!!)
    }

    fun updateProfileTypeToPrestador(id: Int): PersonModel {
        val entity = findPersonById(id)
        entity.profileType = ProfileType.PRESTADOR
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
