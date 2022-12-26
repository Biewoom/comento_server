package com.comento.example.dao.client.message

import com.comento.example.dao.client.example.MessageDto
import org.springframework.stereotype.Component

@Component
class MessageClientFallback: MessageClient {

    override fun getAllMessages(): List<MessageDto> {
        return emptyList()
    }
}