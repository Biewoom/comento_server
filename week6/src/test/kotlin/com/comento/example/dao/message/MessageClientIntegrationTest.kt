package com.comento.example.dao.message

import com.comento.example.dao.client.message.MessageClient
import com.comento.example.logger
import com.comento.example.service.ExampleService
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest
import org.springframework.cloud.netflix.hystrix.HystrixCircuitBreakerAutoConfiguration
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration
import org.springframework.cloud.openfeign.FeignAutoConfiguration
import org.springframework.context.annotation.Import

@RestClientTest(
    components = [MessageClient::class]
)
@Import(RibbonAutoConfiguration::class, HystrixCircuitBreakerAutoConfiguration::class, FeignAutoConfiguration::class)
class MessageClientIntegrationTest {

    @Autowired
    private lateinit var messageClientSut: MessageClient

    @DisplayName("test")
    @Test
    fun `test1`(){
        // given
        val expectedSize = 5

        // when
        val res = messageClientSut.getAllMessages()

        // then
        res.size shouldBe expectedSize
        logger.info { "res: $res" }
    }
}