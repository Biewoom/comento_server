package com.comento.example.domain.message

import javax.persistence.Id


class Message(
    @Id
    val id: String
) {
    lateinit var text: String
}