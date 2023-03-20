package br.edu.ifsp.scl.fazpramim.service

import br.edu.ifsp.scl.fazpramim.model.PermissionModel
import br.edu.ifsp.scl.fazpramim.model.PersonModel
import br.edu.ifsp.scl.fazpramim.model.UserModel
import br.edu.ifsp.scl.fazpramim.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class UserService : UserDetailsService {

    @Autowired
    private lateinit var userRepository: UserRepository

    private val logger = Logger.getLogger(UserService::class.toString())

    override fun loadUserByUsername(username: String?): UserDetails {
        logger.info("Buscando um usuário pelo nome: ${username}")
        val user = userRepository.findByUsername(username)
        return user ?: throw UsernameNotFoundException("Nome ${username} não encontrado")
    }

    fun createUser(person: PersonModel) {
        val user: UserModel = UserModel()
        user.userName = person.email
        user.fullName = person.name
        user.password = passwordEncoder().encode(person.password)
        user.accountNonExpired = true
        user.accountNonLocked = true
        user.credentialsNonExpired = true
        user.enabled = true

        userRepository.save(user)
    }

    private fun passwordEncoder() : PasswordEncoder {
        val encoders: MutableMap<String, PasswordEncoder> = HashMap<String, PasswordEncoder>()
        val pbkdf2Encoder = Pbkdf2PasswordEncoder("", 8, 185000, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256)
        encoders["pbkdf2"] = pbkdf2Encoder
        val passwordEncoder = DelegatingPasswordEncoder("pbkdf2", encoders)
        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder)
        return passwordEncoder
    }
}