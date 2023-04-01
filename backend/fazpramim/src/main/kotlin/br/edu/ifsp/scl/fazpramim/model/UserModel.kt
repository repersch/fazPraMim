package br.edu.ifsp.scl.fazpramim.model

import br.edu.ifsp.scl.fazpramim.enums.ProfileType
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users")
data class UserModel (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "user_name", unique = true)
    var userName: String? = null,

    @Column(name = "full_name")
    var fullName: String? = null,

    @JvmField
    @Column(name = "password")
    var password: String? = null,

    @Column
    var phone: String? = null,

    @Column
    var birthDate: String? = null,

    @Column
    var photo: String? = null,

    @Column(name = "profile_type")
    @Enumerated(EnumType.STRING)
    var profileType: ProfileType = ProfileType.CLIENTE,

    // atributos do SpringSecurity (userDetails)
    @Column(name = "account_non_expired")
    var accountNonExpired: Boolean? = null,

    @Column(name = "account_non_locked")
    var accountNonLocked: Boolean? = null,

    @Column(name = "credentials_non_expired")
    var credentialsNonExpired: Boolean? = null,

    @Column(name = "enabled")
    var enabled: Boolean? = null,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_permission",
        joinColumns = [JoinColumn(name = "id_user")],
        inverseJoinColumns = [JoinColumn(name = "id_permission")]
    )
    var permissions: List<PermissionModel>? = null

) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return permissions!!
    }

    override fun getPassword(): String {
        return password!!
    }

    override fun getUsername(): String {
        return userName!!
    }

    override fun isAccountNonExpired(): Boolean {
        return accountNonExpired!!
    }

    override fun isAccountNonLocked(): Boolean {
        return accountNonLocked!!
    }

    override fun isCredentialsNonExpired(): Boolean {
        return credentialsNonExpired!!
    }

    override fun isEnabled(): Boolean {
        return enabled!!
    }
}
