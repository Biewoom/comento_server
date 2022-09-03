package com.example.week4.domain.person

import com.example.week4.domain.company.Company
import com.example.week4.domain.country.Country
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "person")
data class Person(
    @Column(name = "name")
    val name: String,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [], optional = false)
    @JoinColumn(name = "country",referencedColumnName = "name")
    val country: Country,

){
    @Column(name = "age")
    var age: Int? = null

    @Column(name = "height")
    var height: Int? = null

    @ManyToOne(fetch = FetchType.LAZY, cascade = [], optional = true)
    @JoinColumn(name = "company", referencedColumnName = "name")
    var company: Company? = null

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private var _id: Long = 0L

    val id: Long
        get() = _id
}
