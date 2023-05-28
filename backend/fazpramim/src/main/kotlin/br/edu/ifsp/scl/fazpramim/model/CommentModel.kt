package br.edu.ifsp.scl.fazpramim.model

import jakarta.persistence.*

@Entity
@Table(name = "comments")
data class CommentModel (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    var client: UserModel,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    var provider: ProfileModel,

    @Column
    var comment: String? = null
)
