package com.example.week4.domain.person

import com.example.week4.logger
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest(showSql = true)
class PersonRepositoryTest {

    @Autowired
    private lateinit var personRepository: PersonRepository

    @DisplayName("Age Null test")
    @Test
    fun `age 값이 null 인 값 호출`(){
        // given
        val personId = 1L

        val res = personRepository.findById(personId)

        logger.info { "res: $res"}
    }

}