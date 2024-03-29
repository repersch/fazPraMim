package br.edu.ifsp.scl.fazpramim.repository

import br.edu.ifsp.scl.fazpramim.model.ProfileModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProfileRepository : CrudRepository<ProfileModel, Long> {

    fun findProfileByServiceTypeDescriptionIgnoreCase(serviceTypeDescription: String): List<ProfileModel>

    fun findProfileByUserId(userId: Long): Optional<ProfileModel>

}
