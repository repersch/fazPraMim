package br.edu.ifsp.scl.fazpramim.service

import br.edu.ifsp.scl.fazpramim.enums.Errors
import br.edu.ifsp.scl.fazpramim.exception.NotFoundException
import br.edu.ifsp.scl.fazpramim.model.ServiceTypeModel
import br.edu.ifsp.scl.fazpramim.repository.ServiceTypeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ServiceTypeService {

    @Autowired
    private lateinit var repository: ServiceTypeRepository

    fun findAllServiceType(): List<ServiceTypeModel> {
        return repository.findAll().toList()
    }

    fun findServiceTypeById(id: Long): ServiceTypeModel {
        return repository.findById(id)
            .orElseThrow { NotFoundException(Errors.FPM301.message.format(id), Errors.FPM301.code) }
    }
}