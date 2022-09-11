package com.comento.example.domain.product

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "product")
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var _id: Long? = null
){
    val id: Long
        get() = _id ?: throw IllegalStateException()

    @Column(name = "product_name")
    lateinit var name: String

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    lateinit var memberProducts: List<MemberProduct>
}
