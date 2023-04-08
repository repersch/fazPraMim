package br.edu.ifsp.scl.fazpramim.repository

import br.edu.ifsp.scl.fazpramim.model.ServiceTypeModel
import org.springframework.data.repository.CrudRepository

interface ServiceTypeRepository : CrudRepository<ServiceTypeModel, Long> {

}
