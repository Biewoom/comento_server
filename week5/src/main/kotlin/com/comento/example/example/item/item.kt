package com.comento.example.example.item

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Inheritance
import javax.persistence.InheritanceType
import javax.persistence.Table


@Entity
@Table(name = "item")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 상속 구현 전략 선택
abstract class Item (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private var _id: Long? = null
) : Serializable {
    open var name: String? = null
    open var price = 0
    open var quantity = 0
}

@Entity(name = "album")
class Album: Item() {
    var artist: String? = null
}

@Entity(name = "movie")
class Movie : Item() {
    var director: String? = null
    var actor: String? = null
}

@Entity(name = "book")
class Book : Item() {
    var author: String? = null
}

