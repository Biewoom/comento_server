package com.comento.example.domain.company

import com.comento.example.domain.common.vo.YMD
import com.comento.example.domain.country.Country
import com.comento.example.domain.person.Person
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "company")
open class Company(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private var _id: Long? = null
){
    val id: Long
        get() = _id ?: 0

    @Column(name = "founding_date")
    open var foundingDate: YMD = YMD("2022-09-01")

    @Column(name = "name")
    open lateinit var name: String

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "country", referencedColumnName = "name")
    open lateinit var country: Country

    @OneToMany(targetEntity = Person::class, mappedBy = "company")
    open lateinit var employees: List<Person>
}




