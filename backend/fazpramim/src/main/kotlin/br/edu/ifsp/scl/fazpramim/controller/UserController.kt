package br.edu.ifsp.scl.fazpramim.controller

import br.edu.ifsp.scl.fazpramim.controller.request.PutUserRequest
import br.edu.ifsp.scl.fazpramim.controller.response.UserResponse
import br.edu.ifsp.scl.fazpramim.extension.toResponse
import br.edu.ifsp.scl.fazpramim.extension.toUserModel
import br.edu.ifsp.scl.fazpramim.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
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
    fun findAllUsers(): List<UserResponse> {
        return service.findAllUsers().map { user -> user.toResponse() }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um usuário por ID", description = "Busca um usuário por ID")
    fun findUserById(@PathVariable(value = "id") id: Long): UserResponse {
        return service.findUserById(id).toResponse()
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edita uma pessoa", description = "Edita uma pessoa")
    fun updateUser(@PathVariable id: Long, @RequestBody user: PutUserRequest): UserResponse {
        val userSaved = service.findUserById(id)
        return service.updateUser(user.toUserModel(userSaved)).toResponse()
    }

    @PutMapping("/setPrestador/{id}")
    @Operation(summary = "Edita o tipo de perfil de uma pessoa", description = "Edita o tipo de perfil de uma pessoa")
    fun updateProfileType(@PathVariable id: Long): UserResponse {
        return service.updateProfileTypeToPrestador(id).toResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Exclui uma pessoa", description = "Exclui uma pessoa")
    fun deleteUser(@PathVariable(value = "id") id: Long) {
        service.deleteUser(id)
    }
}
