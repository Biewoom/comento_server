package com.comento.example.domain.member

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "locker")
data class Locker(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "locker_id")
    private var _id: Long? = null
){
    val id: Long
        get() = _id ?: throw IllegalStateException()

    lateinit var name: String
}