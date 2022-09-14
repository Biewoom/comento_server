package com.comento.example.domain.common.entity

import java.io.Serializable
import java.util.Date
import javax.persistence.MappedSuperclass
import javax.persistence.Temporal
import javax.persistence.Version

@MappedSuperclass
abstract class BaseEntity: Serializable {

    @Version
    open val version: Int? = null

    open val creationDate: Date? = null
}