package com.comento.example.domain.company

import com.comento.example.domain.common.vo.YMD
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "company")
data class Company(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private var _id: Long? = null,
) {
    val id: Long
        get() = _id ?: throw RuntimeException()

    @Column(name = "founding_date")
    var foundingDate: YMD = YMD("2022-12-07")

    @Column(name = "name")
    var name: String = ""

    @Column(name = "country")
    var country: String = ""


}


