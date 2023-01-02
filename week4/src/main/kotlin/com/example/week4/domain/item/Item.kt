package com.example.week4.domain.item

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "item")
class Item (
    name: String,
    price: Float,
    quantity: Int,
    kind: Byte,
    author: String? = null,
    actor: String? = null,
    director: String? = null,
    artist: String? = null,
    painter: String? = null,
): Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private var _id: Long? = null

    @Column(name = "name", nullable = false)
    var name: String = name
        protected set

    @Column(name = "price", nullable = false)
    var price: Float = price
        protected set

    @Column(name = "quantity")
    var quantity: Int = quantity
        protected set

    @Column(name = "kind")
    val kind: Byte = kind

    @Column(name = "author", nullable = true, updatable = false)
    val author: String? = author

    @Column(name = "actor", nullable = true, updatable = false)
    val actor: String? = actor

    @Column(name ="director", nullable = true, updatable = false)
    val director: String? = director

    @Column(name = "artist", nullable = true, updatable = false)
    val artist: String? = artist

    @Column(name = "painter", nullable = true, updatable = false)
    val painter: String? = painter

    @Column(name = "description", nullable = true)
    var description: String? = null
        protected set

}