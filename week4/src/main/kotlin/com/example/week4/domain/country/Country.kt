package com.example.week4.domain.country

import com.example.week4.domain.common.PreventAnyUpdate
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "country")
@EntityListeners(PreventAnyUpdate::class)
data class Country(
    @Id @Column(name = "zip_code")
    val zipCode: Long,

): Serializable {
    @Column(name = "name", unique = true)
    var name: String = ""

    @Column(name = "capital_city")
    var capitalCity: String= ""

}

