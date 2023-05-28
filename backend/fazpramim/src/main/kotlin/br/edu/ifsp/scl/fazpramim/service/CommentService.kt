package br.edu.ifsp.scl.fazpramim.service

import br.edu.ifsp.scl.fazpramim.enums.Errors
import br.edu.ifsp.scl.fazpramim.enums.ServiceStatus
import br.edu.ifsp.scl.fazpramim.exception.InvalidEvaluationException
import br.edu.ifsp.scl.fazpramim.exception.NotFoundException
import br.edu.ifsp.scl.fazpramim.model.CommentModel
import br.edu.ifsp.scl.fazpramim.repository.CommentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CommentService {

    @Autowired
    private lateinit var repository: CommentRepository

    @Autowired
    private lateinit var serviceService: ServiceService

    @Autowired
    private lateinit var profileService: ProfileService

    fun findCommentById(id: Long): CommentModel {
        return repository.findById(id)
            .orElseThrow { NotFoundException(Errors.FPM901.message.format(id), Errors.FPM901.code) }
    }

    fun findCommentByProvider(id: Long): List<CommentModel> {
        val providerId = profileService.findProfileByUserId(id).id
        return repository.findCommentByProviderId(providerId!!)
            .orElseThrow { NotFoundException(Errors.FPM902.message.format(id), Errors.FPM902.code) }
    }

    fun createCommentByServiceId(serviceId: Long, comment: String): CommentModel {
        val service = serviceService.findServiceById(serviceId)
        if (service.status != ServiceStatus.FINISHED)
            throw InvalidEvaluationException(Errors.FPM701.message, Errors.FPM701.code)

        val entity = repository.save(CommentModel(
            client = service.client,
            provider = service.provider,
            comment = comment
        ))

        return findCommentById(entity.id)
    }
}
