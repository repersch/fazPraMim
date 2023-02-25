package br.edu.ifsp.scl.fazpramim.repository

import br.edu.ifsp.scl.fazpramim.model.PersonModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : CrudRepository<PersonModel, Int> {
    fun findByNameContainingIgnoreCase(name: String): List<PersonModel>

    fun findByEmail(email: String): PersonModel

    fun existsByEmail(email: String): Boolean

}
