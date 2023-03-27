package br.edu.ifsp.scl.fazpramim.repository

import br.edu.ifsp.scl.fazpramim.model.UserModel
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface UserRepository : CrudRepository<UserModel, Long> {

    @Query("select (count(u) > 0) from UserModel u")
    fun existsByEmail(email: String): Boolean


    @Query("SELECT u FROM UserModel u WHERE u.userName =:userName")
    fun findByUsername(@Param("userName") userName: String?): UserModel?
}
