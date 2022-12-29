package com.example.week4.domain.company

import au.com.console.kassava.kotlinEquals
import au.com.console.kassava.kotlinHashCode
import au.com.console.kassava.kotlinToString
import com.example.week4.domain.common.YMD
import com.example.week4.domain.country.Country
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import kotlin.RuntimeException

@Entity
@Table(name = "company")
class Company (
    name: String,
    foundingYMD: YMD,
    country: Country
): Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private var _id: Long? = null

    @Column(name = "name", nullable = false)
    var name: String = name
        protected set

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country", nullable = false, referencedColumnName = "name")
    var country: Country = country
        protected set

    @Column(name = "founding_date", nullable = false, updatable = false)
    val foundingYMD: YMD = foundingYMD

    val id
        get() = _id ?: throw RuntimeException()

    override fun equals(other: Any?): Boolean = kotlinEquals(other, properties = arrayOf(Company::_id))
    override fun hashCode() = kotlinHashCode(properties = arrayOf(Company::_id))
    override fun toString(): String = kotlinToString(properties = arrayOf(
        Company::_id,
        Company::name,
        Company::foundingYMD,
        Company::country)
    )
}


