package com.example.week4.domain.person

import au.com.console.kassava.kotlinEquals
import au.com.console.kassava.kotlinHashCode
import au.com.console.kassava.kotlinToString
import com.example.week4.domain.company.Company
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
import javax.persistence.UniqueConstraint
import kotlin.RuntimeException

@Entity
@Table(name = "person",  uniqueConstraints = [
    UniqueConstraint(name = "uc_person_age", columnNames = ["age"])
])
class Person(
    name: String,
    country: Country
): Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private var _id: Long? = null

    @Column(name = "name")
    var name: String = name
        protected set

    @ManyToOne(fetch = FetchType.LAZY, cascade = [], optional = false)
    @JoinColumn(name = "country",referencedColumnName = "name")
    var country: Country = country
        protected set

    @ManyToOne(fetch = FetchType.LAZY, cascade = [], optional = true)
    @JoinColumn(name = "company", referencedColumnName = "name", nullable = true)
    var company: Company? = null
        protected set

    @Column(name = "age", nullable = true)
    var age: Int = 0

    @Column(name = "height", nullable = true)
    var height: Int? = null

    val id: Long
        get() = _id ?: throw RuntimeException()

    fun updateName(newName: String) = run { this.name = newName }
    fun moveCompany(company: Company) = run { this.company = company }

    override fun equals(other: Any?): Boolean = kotlinEquals(other, properties = arrayOf(Person::_id))
    override fun hashCode() = kotlinHashCode(properties = arrayOf(Person::_id))
    override fun toString(): String = kotlinToString(properties = arrayOf(
        Person::_id,
        Person::name,
        Person::country)
    )
}
