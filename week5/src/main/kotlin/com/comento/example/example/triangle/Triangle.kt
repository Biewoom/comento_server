package com.comento.example.example.triangle

import javax.persistence.AttributeOverride
import javax.persistence.AttributeOverrides
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "triangle")
data class Triangle (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "triangle_id")
    private var id: Long? = null
) {
    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "x", column = Column(name = "vertexOneX")),
        AttributeOverride(name = "y", column = Column(name = "vertexOneY"))
    )
    var vertexOne: Coordinate = Coordinate(0L, 0L)

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "x", column = Column(name = "vertexTwoX")),
        AttributeOverride(name = "y", column = Column(name = "vertexTwoY"))
    )
    var vertexTwo: Coordinate = Coordinate(0L, 0L)

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "x", column = Column(name = "vertexThirdX")),
        AttributeOverride(name = "y", column = Column(name = "vertexThirdY"))
    )
    var vertexThird: Coordinate = Coordinate(0L, 0L)
}

@Embeddable
data class Coordinate(
    var x: Long,
    var y: Long
)