package com.comento.example.domain.message

interface MessageRepository {

    fun findMessageById(id: String): Message

    fun findMessages(): List<Message>
}