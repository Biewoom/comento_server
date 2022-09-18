package com.comento.example.domain.person

import com.comento.example.domain.common.enums.Gender
import com.comento.example.domain.company.Company
import com.comento.example.domain.country.Country
import javax.persistence.*

@Entity
@Table(name = "person")
open class Person(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", precision = 255)
    private var _id: Long? = null
){
    constructor(): this(null)
    val id: Long
        get() = _id ?: throw RuntimeException()

    @Column(name = "name", length = 30)
    open lateinit var name: String

    @Column(name = "gender")
    @Enumerated(value = EnumType.ORDINAL)
    open lateinit var gender: Gender

    @Column(name = "age", precision = 10)
    open var age: Int? = null

    @Column(name = "height", precision = 10)
    open var height: Int? = null

    @Column(name = "weight", precision = 10)
    open var weight: Int? = null

    @Column(name = "is_married")
    open var isMarried: Boolean? = null

    // FK
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country", referencedColumnName = "name")
    open lateinit var country: Country

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "company", nullable = true)
    open var company: Company? = null

    override fun toString(): String {
        return "Person(name='$name', gender=$gender, country='$country', age=$age)"
    }

    fun updateId(_id: Number){
        this._id = _id.toLong()
    }

}


