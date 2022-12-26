package com.comento.example.dao

import com.comento.example.dao.client.example.ExampleClient
import com.comento.example.domain.message.Message
import com.comento.example.domain.message.MessageRepository
import org.springframework.stereotype.Repository

@Repository
class HttpMessageRepository(
    private val exampleClient: ExampleClient
): MessageRepository {

    override fun findMessageById(id: String): Message {
        val messageDto = exampleClient.getOneMessage(id)
        return Message(messageDto.id).apply { text = messageDto.text ?: "" }
    }

    override fun findMessages(): List<Message> {
        return exampleClient.getAllMessage().map { Message(it.id).apply { text = it.text ?: "" } }
    }
}