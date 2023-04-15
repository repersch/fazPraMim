package br.edu.ifsp.scl.fazpramim.repository

import br.edu.ifsp.scl.fazpramim.model.ServiceModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ServiceRepository : CrudRepository<ServiceModel, Long> {

}
