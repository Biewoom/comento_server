package com.comento.example.example.member

import com.comento.example.example.product.MemberProduct
import com.comento.example.example.team.Team
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "member")
data class Member (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private var _id: Long? = null
){
    val id: Long
        get() = _id ?: throw IllegalStateException()

    @Column(name = "username", nullable = true)
    lateinit var name: String

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = true)
    lateinit var team: Team

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "locker_id", nullable = true)
    var locker: Locker? = null

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    lateinit var memberProducts: List<MemberProduct>

    override fun toString(): String {
        return "Member(id=$id, name='$name')"
    }
}
