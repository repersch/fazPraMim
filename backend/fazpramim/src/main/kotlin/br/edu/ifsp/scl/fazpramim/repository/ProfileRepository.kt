package br.edu.ifsp.scl.fazpramim.repository

import br.edu.ifsp.scl.fazpramim.model.ProfileModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfileRepository : CrudRepository<ProfileModel, Int> {

}
