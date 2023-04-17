package br.edu.ifsp.scl.fazpramim.service

import br.edu.ifsp.scl.fazpramim.controller.request.PostUserRequest
import br.edu.ifsp.scl.fazpramim.enums.Errors
import br.edu.ifsp.scl.fazpramim.enums.ProfileType
import br.edu.ifsp.scl.fazpramim.exception.EntityAlreadyExistsExeption
import br.edu.ifsp.scl.fazpramim.exception.NotFoundException
import br.edu.ifsp.scl.fazpramim.extension.toUserModel
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.logging.Logger

@Service
class UserService : UserDetailsService {

    @Autowired
    private lateinit var repository: UserRepository

    private val logger = Logger.getLogger(UserService::class.toString())

    fun findAllUsers(): List<UserModel> {
        return repository.findAll().toList()
    }

    fun findUserById(id: Long): UserModel {
        return repository.findById(id)
            .orElseThrow { NotFoundException(Errors.FPM101.message.format(id), Errors.FPM101.code) }
    }

    fun createUser(user: PostUserRequest): UserModel {
        if (userNameAvailable(user.username)) {
            val entity = repository.save(user.toUserModel())
            return findUserById(entity.id)
        } else {
            throw EntityAlreadyExistsExeption(Errors.FPM102.message.format(user.id), Errors.FPM102.code)
        }
    }

    fun updateUser(user: UserModel): UserModel {
        val entity = findUserById(user.id)
        entity.fullName = user.fullName
        entity.userName = user.username
        entity.password = user.password
        entity.phone = user.phone
        entity.photo = user.photo
        entity.birthDate = user.birthDate
        repository.save(entity)
        return findUserById(entity.id)
    }

    fun updateProfileTypeToPrestador(id: Long): UserModel {
        val entity = findUserById(id)
        entity.profileType = ProfileType.PRESTADOR
        repository.save(entity)
        return findUserById(entity.id)
    }

    fun updateProfileTypeToCliente(id: Long): UserModel {
        val entity = findUserById(id)
        entity.profileType = ProfileType.CLIENTE
        repository.save(entity)
        return findUserById(entity.id)
    }

    fun deleteUser(id: Long) {
        val entity = findUserById(id)
        repository.delete(entity)
    }

    fun userNameAvailable(userName: String): Boolean {
        return !repository.existsByUserName(userName)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        logger.info("Buscando um usuário pelo nome: ${username}")
        val user = repository.findByUserName(username)
        return user ?: throw UsernameNotFoundException("Nome ${username} não encontrado")
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