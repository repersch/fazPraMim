package br.edu.ifsp.scl.fazpramim.controller

import br.edu.ifsp.scl.fazpramim.model.User
import br.edu.ifsp.scl.fazpramim.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    private lateinit var service: UserService

    @GetMapping
    fun findAllUsers(): List<User> {
        return service.findAllUsers()
    }

    @GetMapping("/{id}")
    fun findUserById(@PathVariable(value = "id") id: Long): User {
        return service.findUserById(id)
    }

    @PostMapping
    fun createUser(@RequestBody user: User): User {
        return service.createUser(user)
    }

    @PutMapping
    fun updateUser(@RequestBody user: User): User {
        return service.updateUser(user)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable(value = "id") id: Long): ResponseEntity<*> {
        service.deleteUser(id)
        return ResponseEntity.noContent().build<Any>()
    }
}
