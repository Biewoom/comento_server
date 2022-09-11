package com.comento.example.service

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class PersonServiceIntegrationTest {

    @Autowired
    private lateinit var personService: PersonService

    @DisplayName("실제 Test 환경과 연동된 DB위에 findBlindDatCoupleList Logic 검증")
    @Test
    fun `test findBlindDatCoupleList`(){
        // given
        val ageDiff = 5
        val expectedSize = 1

        // when
        val res = personService.findBlindDateCoupleList(ageDiff)

        // then
        res.size shouldBe expectedSize
    }

}