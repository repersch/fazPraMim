package br.edu.ifsp.scl.fazpramim.controller

import br.edu.ifsp.scl.fazpramim.controller.request.LoginRequest
import br.edu.ifsp.scl.fazpramim.service.AuthService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Authentication Endpoint")
@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping( "/signin")
    fun signin(@RequestBody data: LoginRequest?) : ResponseEntity<out Any> {
        return if (data!!.email.isNullOrBlank() || data.password.isNullOrBlank())
            ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Requisição inválida.")
        else authService.signin(data!!)
    }
}
