package com.comento.example.service

import com.comento.example.dao.client.example.ExampleClient
import com.comento.example.domain.message.MessageRepository
import org.springframework.stereotype.Service

@Service
class FeignExampleService(
    private val exampleClient: ExampleClient,
    private val messageRepository: MessageRepository
){

    fun useExampleClientDirectly(){
        val ll = exampleClient.getAllMessage()
    }

    fun useMessageRepository() {
        val ll  = messageRepository.findMessages()
    }

}