package com.comento.example.dao.client.example

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(
    name = "exampleClient",
    url = "\${feign.client.config.exampleClient.url}",
    path = "/api/v1/messages",
    configuration = [ExampleClientConfiguration::class], )
interface ExampleClient {

    @GetMapping("/all", produces = ["application/json"])
    fun getAllMessage(): List<MessageDto>

    @GetMapping("/{id}", produces = ["application/json"])
    fun getOneMessage(@PathVariable("id") id: String): MessageDto

    @PostMapping(consumes = ["application/json"])
    fun sendMessages(@RequestBody messageDto: MessageDto): MessageDto
}


