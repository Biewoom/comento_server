package com.comento.example.domain.example.inheritance

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Inheritance
import javax.persistence.InheritanceType


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 상속 구현 전략 선택
abstract class Item (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var _id: Long? = null
) : Serializable {
    protected open var name: String? = null
    protected open var price = 0
}

@Entity
class Album: Item() {
    var artist: String? = null
}

@Entity
class Movie : Item() {
    var director: String? = null
    var actor: String? = null
}

@Entity
class Book : Item() {
    var author: String? = null
    var isbn: String? = null
}

