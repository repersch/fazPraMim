package br.edu.ifsp.scl.fazpramim.controller

import br.edu.ifsp.scl.fazpramim.controller.response.CommentResponse
import br.edu.ifsp.scl.fazpramim.extension.toResponse
import br.edu.ifsp.scl.fazpramim.service.CommentService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/comments")
@Tag(name = "Comments", description = "Endpoints para gerenciar comentários")
class CommentController(
    private val service: CommentService
) {

    @GetMapping("/{commentId}")
    @Operation(summary = "Busca um comentário por ID", description = "Busca um comentário por ID")
    fun findCommentById(@PathVariable commentId: Long): CommentResponse {
        return service.findCommentById(commentId).toResponse()
    }

    @GetMapping("/provider/{userId}")
    @Operation(summary = "Busca todos os comentários vinculados ao prestador", description = "Busca todos os comentários vinculados ao userID do prestador")
    fun findCommentByProvider(@PathVariable userId: Long): List<CommentResponse> {
        return service.findCommentByProvider(userId).map { s -> s.toResponse() }
    }

    @PostMapping("/{serviceId}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria um comentário", description = "Cria um comentário")
    fun createComment(@PathVariable serviceId: Long, @RequestBody comment: String): CommentResponse {
        return service.createCommentByServiceId(serviceId, comment).toResponse()
    }
}
