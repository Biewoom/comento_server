package com.comento.example.dao.client.message

import com.comento.example.dao.client.example.MessageDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(
    name = "messageClient",
    url = "\${feign.client.config.messageClient.url}",
    path = "/api/v1/messages",
    configuration = [MessageClientConfiguration::class],
    fallback = MessageClientFallback::class
)
interface MessageClient {

    @GetMapping("/all", produces = ["application/json"])
    fun getAllMessages(): List<MessageDto>
}