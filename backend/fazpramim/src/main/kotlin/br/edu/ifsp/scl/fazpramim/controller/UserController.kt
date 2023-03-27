package br.edu.ifsp.scl.fazpramim.controller

import br.edu.ifsp.scl.fazpramim.model.UserModel
import br.edu.ifsp.scl.fazpramim.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
@Tag(name = "People", description = "Endpoints para gerenciar pessoas")
class UserController (
    val service: UserService
) {

    @GetMapping
    @Operation(summary = "Busca todos os usuários", description = "Busca todos os usuários")
    fun findAllUsers(@RequestHeader("Authorization") token: String?): List<UserModel> {
        return service.findAllPersons()
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um usuário por ID", description = "Busca um usuário por ID")
    fun findUserById(@PathVariable(value = "id") id: Long): UserModel {
        return service.findUserById(id)
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edita uma pessoa", description = "Edita uma pessoa")
    fun updateUser(@PathVariable id: Long, @RequestBody @Valid user: UserModel): UserModel {
        val personSaved = service.findUserById(id)
        return service.updateUser(user)
    }

    @PutMapping("/setPrestador/{id}")
    @Operation(summary = "Edita o tipo de perfil de uma pessoa", description = "Edita o tipo de perfil de uma pessoa")
    fun updateProfileType(@PathVariable id: Long): UserModel {
        return service.updateProfileTypeToPrestador(id)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Exclui uma pessoa", description = "Exclui uma pessoa")
    fun deletePerson(@PathVariable(value = "id") id: Long) {
        service.deleteUser(id)
    }
}
