package com.comento.example.domain.common.entity

import java.io.Serializable
import javax.persistence.MappedSuperclass
import javax.persistence.Version

@MappedSuperclass
abstract class BaseEntity: Serializable {

    @Version
    open val version: Int? = null
}