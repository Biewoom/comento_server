package com.comento.example.domain.product

import com.comento.example.domain.member.Member
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "member_product")
data class MemberProduct(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var _id: Long? = null
) {
    val id: Long
        get() = _id ?: throw IllegalStateException()

    @ManyToOne
    @JoinColumn(name = "member_id")
    lateinit var member: Member

    @ManyToOne
    @JoinColumn(name = "product_id")
    lateinit var product: Product

    var price: Long = 0L
    var count: Long = 0L
}