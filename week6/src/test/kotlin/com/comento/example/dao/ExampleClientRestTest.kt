package com.comento.example.dao

import com.comento.example.dao.client.example.ExampleClient
import com.comento.example.dao.client.example.ExampleClientFallback
import com.comento.example.dao.client.example.MessageDto
import com.comento.example.toJson
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.anyUrl
import com.github.tomakehurst.wiremock.client.WireMock.equalToJson
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.post
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

private const val portNumber: Int = 100

@RestClientTest(
    properties = [
        "feign.client.config.exampleClient.url=http://localhost:$portNumber",
        "logging.level.root=debug"
     ],
    components = [ExampleClient::class]
)
@Import(RibbonAutoConfiguration::class, HystrixCircuitBreakerAutoConfiguration::class, FeignAutoConfiguration::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ExampleClientRestTest {

    private val wireMockServer: WireMockServer = WireMockServer(portNumber)

    @Autowired
    private lateinit var exampleClientSut: ExampleClient

    @BeforeAll
    fun setUp(){
        wireMockServer.start()
    }

    @DisplayName("test example clinet")
    @Test
    fun `success test`(){
        // given
        wireMockServer.stubFor(
            get(anyUrl())
                .willReturn(aResponse()
                    .withHeader("Content-Type", "application/json; charset=utf-8")
                    .withStatus(HttpStatus.SC_OK)
                    .withBody(listOf(MessageDto("mock", "mock")).toJson()))
        )

        // when
        val res = exampleClientSut.getAllMessage()
        val res1 = exampleClientSut.getAllMessage()
        val res2 = exampleClientSut.getAllMessage()


        // then
        res.size shouldBe 1
        res.first().id shouldBe  "mock"
    }

    @DisplayName("fallback client test")
    @Test
    fun `fallback test`(){
        // given
        wireMockServer.stubFor(
            get(anyUrl())
                .willReturn(aResponse()
                    .withHeader("Content-Type", "application/json; charset=utf-8")
                    .withStatus(HttpStatus.SC_NOT_FOUND))
        )

        // when
        val res = exampleClientSut.getAllMessage()

        // then
        res shouldBe ExampleClientFallback().getAllMessage()
    }

    @DisplayName("test example client2")
    @Test
    fun `success test2`(){
        // given
        wireMockServer.stubFor(
            get(anyUrl())
                .willReturn(aResponse()
                    .withHeader("Content-Type", "application/json; charset=utf-8")
                    .withStatus(HttpStatus.SC_OK)
                    .withFixedDelay(3001)
                    .withBody(MessageDto("mock", "mock").toJson()))
        )

        // when
        val res = exampleClientSut.getOneMessage("mock")

        // then
        res.id shouldBe  "mock"
    }

    @DisplayName("test example client3")
    @Test
    fun `success test3`(){
        // given
        val messageDto = MessageDto("id1","text")
        wireMockServer.stubFor(
            post(anyUrl())
                .withRequestBody(equalToJson(messageDto.toJson(),true,true))
                .willReturn(aResponse()
                    .withStatus(HttpStatus.SC_OK)
                    .withHeader("Content-Type", "application/json")
                    .withBody(messageDto.toJson())
            )
        )
        // when
        val res = exampleClientSut.sendMessages(messageDto)

        // then
        res.id shouldBe "id1"
        res.text shouldBe "text"
    }



}