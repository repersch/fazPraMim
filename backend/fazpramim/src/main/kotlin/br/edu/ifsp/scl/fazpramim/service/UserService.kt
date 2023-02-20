package br.edu.ifsp.scl.fazpramim.service

import br.edu.ifsp.scl.fazpramim.exception.ResourceNotFoundException
import br.edu.ifsp.scl.fazpramim.model.User
import br.edu.ifsp.scl.fazpramim.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    private lateinit var repository: UserRepository

    fun findAllUsers(): List<User> {
        return repository.findAll()
    }

    fun findUserById(id: Long): User {
        return repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No User found for this ID!") }
    }

    fun createUser(user: User): User {
        val entity = repository.save(user)
        return findUserById(entity.id)
    }

    fun updateUser(user: User): User {
        val entity = findUserById(user.id)

        entity.name = user.name
        entity.email = user.email
        entity.password = user.password
        entity.phone = user.phone
        entity.birthDate = user.birthDate

        return repository.save(entity)
    }

    fun deleteUser(id: Long) {
        val entity = findUserById(id)
        repository.delete(entity)
    }
}
