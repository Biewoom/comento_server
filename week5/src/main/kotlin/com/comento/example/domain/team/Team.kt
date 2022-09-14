package com.comento.example.domain.team

import com.comento.example.domain.member.Member
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "team")
data class Team (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private var _id: Long? = null
){
    lateinit var name: String

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    lateinit var members: List<Member>
}
