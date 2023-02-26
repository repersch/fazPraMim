package br.edu.ifsp.scl.fazpramim.service

import br.edu.ifsp.scl.fazpramim.enums.Errors
import br.edu.ifsp.scl.fazpramim.enums.Profile
import br.edu.ifsp.scl.fazpramim.exception.EntityAlreadyExistsExeption
import br.edu.ifsp.scl.fazpramim.exception.NotFoundException
import br.edu.ifsp.scl.fazpramim.model.PersonModel
import br.edu.ifsp.scl.fazpramim.repository.PersonRepository
import br.edu.ifsp.scl.fazpramim.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService(
    val repository: UserRepository
): UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = repository.findByUsername(username)
        return user ?: throw NotFoundException("Usuário não encontrado.", "999")
    }
}
