package br.edu.ifsp.scl.fazpramim.model

import br.edu.ifsp.scl.fazpramim.enums.PersonStatus
import br.edu.ifsp.scl.fazpramim.enums.Profile
import jakarta.persistence.*

@Entity(name = "person")
data class PersonModel (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    var email: String,

    @Column
    var password: String,

    @Column
    var phone: String,

    @Column
    var birthDate: String,

    @Column(name = "person_status")
    @Enumerated(EnumType.STRING)
    var personStatus: PersonStatus?,

    @Column(name = "role")
    @ElementCollection(targetClass = Profile::class, fetch = FetchType.EAGER)
    @CollectionTable(name = "profile_roles", joinColumns = [JoinColumn(name = "person_id")])
    @Enumerated(EnumType.STRING)
    var roles: Set<Profile> = setOf()

) {
    constructor() : this(null, "", "", "", "", "", null) {}
}
