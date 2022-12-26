package com.comento.example.dao

import com.comento.example.dao.client.example.ExampleClient
import com.comento.example.dao.client.example.MessageDto
import com.comento.example.logger
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class ExampleClientIntegrationTest {

    @Autowired
    private lateinit var exampleClientSut: ExampleClient

    @DisplayName("test example client3")
    @Test
    fun `success test3`(){
        // given
        val messageDto = MessageDto("id1","text")

        // when
        val res = exampleClientSut.sendMessages(messageDto)
        val result = exampleClientSut.getAllMessage()

        // then
        res.id shouldBe "id1"
        res.text shouldBe "text"

        logger.info { "result: $result" }
    }

}