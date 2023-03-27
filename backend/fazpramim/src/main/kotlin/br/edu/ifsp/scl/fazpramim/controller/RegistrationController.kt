package br.edu.ifsp.scl.fazpramim.controller

import br.edu.ifsp.scl.fazpramim.model.UserModel
import br.edu.ifsp.scl.fazpramim.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/registration")
@Tag(name = "Registration", description = "Endpoints para criar um novo usuário")
class RegistrationController(
    private val service: UserService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria uma pessoa", description = "Cria uma pessoa")
    fun createPerson(@RequestBody @Valid user: UserModel): UserModel {
        return service.createUser(user)
    }
}