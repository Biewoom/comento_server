package com.example.week4.domain.company

import com.example.week4.domain.country.Country
import com.example.week4.domain.person.Person
import java.io.Serializable
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
@Table(name = "company")
data class Company (
    @Column(name = "name")
    val name: String,

    @Column(name = "founding_date")
    val foundingYMD: YMD,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country")
    val country: Country,
): Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private var _id: Long = 0L

    val id: Long
        get() = _id
}


