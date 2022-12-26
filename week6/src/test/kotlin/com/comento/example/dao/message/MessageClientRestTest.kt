package com.comento.example.dao.message

import com.comento.example.dao.client.example.MessageDto
import com.comento.example.dao.client.message.MessageClient
import com.comento.example.toJson
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.apache.http.HttpStatus
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest
import org.springframework.cloud.netflix.hystrix.HystrixCircuitBreakerAutoConfiguration
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration
import org.springframework.cloud.openfeign.FeignAutoConfiguration
import org.springframework.context.annotation.Import
import java.net.SocketTimeoutException

private const val portNumber: Int = 1000

@RestClientTest(
    properties = [
        "feign.client.config.messageClient.url=http://localhost:$portNumber",
        "logging.level.root=debug"
    ],
    components = [MessageClient::class]
)
@Import(RibbonAutoConfiguration::class, HystrixCircuitBreakerAutoConfiguration::class, FeignAutoConfiguration::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class MessageClientRestTest {

    private val wireMockServer: WireMockServer = WireMockServer(portNumber)

    @Autowired
    private lateinit var messageClientSut: MessageClient

    @BeforeAll
    fun setUp(){
        wireMockServer.start()
    }

    @DisplayName("get All message")
    @Test
    fun `test1`(){
        // given
        val mockInput = listOf(MessageDto("mock", "mock"))
        wireMockServer.stubFor(
            WireMock.get(WireMock.anyUrl())
                .willReturn(
                    WireMock.aResponse()
                    .withHeader("Content-Type", "application/json; charset=utf-8")
                    .withStatus(HttpStatus.SC_OK)
                    .withBody(mockInput.toJson()))
        )

        // when
        val res = messageClientSut.getAllMessages()

        // then
        res shouldBe mockInput
    }

    @DisplayName("fallback All message")
    @Test
    fun `test2`(){
        // given
        val mockInput = listOf(MessageDto("mock", "mock"))
        wireMockServer.stubFor(
            WireMock.get(WireMock.anyUrl())
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(HttpStatus.SC_SERVICE_UNAVAILABLE)
        ))

        // when
        val res = messageClientSut.getAllMessages()

        // then
        res shouldBe emptyList()
    }

    @DisplayName("timeout All message")
    @Test
    fun `test3`(){
        // given
        val mockInput = listOf(MessageDto("mock", "mock"))
        wireMockServer.stubFor(
            WireMock.get(WireMock.anyUrl())
                .willReturn(
                    WireMock.aResponse()
                        .withHeader("Content-Type", "application/json; charset=utf-8")
                        .withStatus(HttpStatus.SC_OK)
                        .withBody(mockInput.toJson())
                        .withFixedDelay(1000)
                )
            )

        // when & then
        shouldThrow<SocketTimeoutException> {
            messageClientSut.getAllMessages()
        }
    }




}