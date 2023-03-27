package br.edu.ifsp.scl.fazpramim.model

import br.edu.ifsp.scl.fazpramim.enums.ProfileType
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.ArrayList

@Entity
@Table(name = "users")
class UserModel : UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "user_name", unique = true)
    var userName: String? = null

    @Column(name = "full_name")
    var fullName: String? = null

    @JvmField
    @Column(name = "password")
    var password: String? = null

    @Column
    var phone: String = ""

    @Column
    var birthDate: String = ""

    @Column
    var photo: String = ""

    @Column(name = "profile_type")
    // @ElementCollection(targetClass = ProfileType::class, fetch = FetchType.EAGER)
    // @CollectionTable(name = "profile_type_roles", joinColumns = [JoinColumn(name = "person_id")])
    @Enumerated(EnumType.STRING)
    var profileType: ProfileType = ProfileType.CLIENTE

    @Column(name = "account_non_expired")
    var accountNonExpired: Boolean? = null

    @Column(name = "account_non_locked")
    var accountNonLocked: Boolean? = null

    @Column(name = "credentials_non_expired")
    var credentialsNonExpired: Boolean? = null

    @Column(name = "enabled")
    var enabled: Boolean? = null

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_permission",
        joinColumns = [JoinColumn(name = "id_user")],
        inverseJoinColumns = [JoinColumn(name = "id_permission")]
    )
    var permissions: List<PermissionModel>? = null

    val roles: List<String?>
        get(){
            val roles: MutableList<String?> = ArrayList()
            for (permission in permissions!!) {
                roles.add(permission.description)
            }
            return roles
        }

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
