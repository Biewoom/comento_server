package com.comento.example.dao.client.example

import org.springframework.stereotype.Component

@Component
class ExampleClientFallback: ExampleClient {

    override fun getAllMessage(): List<MessageDto> {
        return listOf(MessageDto("fallback", "fallback_message"))
    }

    override fun getOneMessage(id: String): MessageDto {
        return MessageDto("fallback", "fallback_meessage")
    }

    override fun sendMessages(messageDto: MessageDto): MessageDto {
        return MessageDto("fallback", "fallback_meessage")
    }
}