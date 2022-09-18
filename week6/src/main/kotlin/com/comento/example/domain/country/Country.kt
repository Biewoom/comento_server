package com.comento.example.domain.country

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "country")
open class Country (
    @Id @Column(name = "zip_code")
    open val zipCode: Long,
): Serializable {

    @Column(name = "name")
    open lateinit var name: String

    @Column(name = "capital_city")
    open lateinit var capitalCity: String

}
