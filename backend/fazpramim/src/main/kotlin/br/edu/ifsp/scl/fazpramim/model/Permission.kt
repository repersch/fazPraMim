package br.edu.ifsp.scl.fazpramim.model

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority

@Entity
@Table(name = "permission")
class Permission: GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @Column
    var description: String? = null


    override fun getAuthority() = description!!
}
