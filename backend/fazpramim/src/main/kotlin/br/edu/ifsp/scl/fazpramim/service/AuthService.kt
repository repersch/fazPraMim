package br.edu.ifsp.scl.fazpramim.service

import br.edu.ifsp.scl.fazpramim.controller.request.LoginRequest
import br.edu.ifsp.scl.fazpramim.controller.response.LoginResponse
import br.edu.ifsp.scl.fazpramim.repository.UserRepository
import br.edu.ifsp.scl.fazpramim.security.jwt.JwtTokenProvider
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val repository: UserRepository,
    private val authenticationManager: AuthenticationManager,
    private val tokenProvider: JwtTokenProvider
) {

    fun signin(data: LoginRequest): ResponseEntity<*> {
        return try {
            val email = data.email
            val password = data.password
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(email, password))
            val user = repository.findByUsername(email!!)
            val tokenResponse: LoginResponse = if (user != null) {
                tokenProvider.createAccessToken(email!!, user.roles)
            } else {
                throw UsernameNotFoundException("E-mail $email não encontrado.")
            }
            ResponseEntity.ok(tokenResponse)
        } catch (ex: AuthenticationException) {
            throw BadCredentialsException("E-mail e/ou senha inválido/s.")
        }

    }

}
