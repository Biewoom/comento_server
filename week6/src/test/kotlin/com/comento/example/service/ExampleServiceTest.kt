package com.comento.example.service

import com.comento.example.domain.common.enums.Gender
import com.comento.example.domain.country.CountryRepository
import com.comento.example.domain.person.PersonRepository
import com.comento.example.logger
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.annotation.DirtiesContext

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
internal class ExampleServiceTest {

    @Autowired
    lateinit var countryRepository: CountryRepository

    @Autowired
    lateinit var personRepository: PersonRepository

    lateinit var exampleService: ExampleService

    @BeforeEach
    fun setUp(){
        exampleService = ExampleService(countryRepository, personRepository)
    }

    @DisplayName("Get Average Of Height Test")
    @Test
    fun `test1`(){
        // given
        val countryName = "KOREA"
        val gender = Gender.MALE

        // when
        val res = exampleService.getAverageOfHeightOfPersonsInCountryName(countryName, gender)

        // then
        logger.info { "res: $res" }
        logger.info { "res: $res" }
    }

}