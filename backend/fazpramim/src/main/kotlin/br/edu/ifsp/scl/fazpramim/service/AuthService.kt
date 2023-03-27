package br.edu.ifsp.scl.fazpramim.service

import br.edu.ifsp.scl.fazpramim.data.AccountCredentialsVO
import br.edu.ifsp.scl.fazpramim.data.TokenVO
import br.edu.ifsp.scl.fazpramim.repository.UserRepository
import br.edu.ifsp.scl.fazpramim.security.jwt.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class AuthService {

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var tokenProvider: JwtTokenProvider

    @Autowired
    private lateinit var repository: UserRepository

    private val logger = Logger.getLogger(AuthService::class.java.name)

    fun signin(data: AccountCredentialsVO) : ResponseEntity<*> {
        logger.info("Tentando logar o usuário ${data.username}")
        return try {
            val username = data.username
            val password = data.password
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
            val user = repository.findByUsername(username)
            val tokenResponse: TokenVO = if (user != null) {
                tokenProvider.createAccessToken(username!!, user.id, user.roles)
            } else {
                throw UsernameNotFoundException("Nome do usuário $username não encontrado!")
            }
            ResponseEntity.ok(tokenResponse)
        } catch (e: AuthenticationException) {
            throw BadCredentialsException("Nome de usuário ou senha inválido!")
        }
    }

    fun refreshToken(username: String, refreshToken: String) : ResponseEntity<*> {
        logger.info("Tentando recarregar o token do usuário: $username")

        val user = repository.findByUsername(username)
        val tokenResponse: TokenVO = if (user != null) {
            tokenProvider.refreshToken(refreshToken, user.id)
        } else {
            throw UsernameNotFoundException("Nome do usuário $username não encontrado!")
        }
        return ResponseEntity.ok(tokenResponse)
    }
}
