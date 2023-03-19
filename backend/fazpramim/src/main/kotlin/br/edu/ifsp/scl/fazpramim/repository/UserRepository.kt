package br.edu.ifsp.scl.fazpramim.repository

import br.edu.ifsp.scl.fazpramim.model.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserRepository : JpaRepository<UserModel?, Long?> {

    @Query("SELECT u FROM UserModel u WHERE u.userName =:userName")
    fun findByUsername(@Param("userName") userName: String?): UserModel?
}
