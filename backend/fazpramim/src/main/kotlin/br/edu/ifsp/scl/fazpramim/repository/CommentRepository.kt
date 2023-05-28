package br.edu.ifsp.scl.fazpramim.repository

import br.edu.ifsp.scl.fazpramim.model.CommentModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CommentRepository : CrudRepository<CommentModel, Long> {

    fun findCommentByProviderId(providerId: Long): Optional<List<CommentModel>>
}
