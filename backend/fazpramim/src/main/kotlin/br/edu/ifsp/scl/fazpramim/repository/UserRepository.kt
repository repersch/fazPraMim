package br.edu.ifsp.scl.fazpramim.repository

import br.edu.ifsp.scl.fazpramim.model.UserModel
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface UserRepository : CrudRepository<UserModel, Long> {

    fun existsByUserName(userName: String): Boolean

    fun findByUserName(@Param("userName") userName: String?): UserModel?
}
