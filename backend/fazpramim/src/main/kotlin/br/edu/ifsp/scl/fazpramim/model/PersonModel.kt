package br.edu.ifsp.scl.fazpramim.model

import br.edu.ifsp.scl.fazpramim.enums.PersonStatus
import br.edu.ifsp.scl.fazpramim.enums.ProfileType
import jakarta.persistence.*

@Entity
@Table(name = "persons")
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

    @Column
    var photo: String,

    @Column(name = "person_status")
    @Enumerated(EnumType.STRING)
    var personStatus: PersonStatus?,

    @Column(name = "role")
   // @ElementCollection(targetClass = ProfileType::class, fetch = FetchType.EAGER)
   // @CollectionTable(name = "profile_type_roles", joinColumns = [JoinColumn(name = "person_id")])
    @Enumerated(EnumType.STRING)
    var profileType: ProfileType,

)
