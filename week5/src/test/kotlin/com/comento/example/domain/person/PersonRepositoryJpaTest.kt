package com.comento.example.domain.person

import com.comento.example.domain.common.enums.Gender
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
internal class PersonRepositoryJpaTest {

    @Autowired
    lateinit var personRepository: PersonRepository

    @Test
    fun check_exists_by_id(){
        // given
        val _id = 1

        // when
        val res = personRepository.existsBy_id(1)

        // then
        res shouldBe true
    }

    @Test
    fun get_first_check_id(){
        // given
        val _id = 2
        val expectedRes = Person(
            name = "Alice",
            Gender.FEMALE,
            country = "USA"
        ).apply {
            age = 20
            height = 170
            weight = 55
            isMarried = true
        }

        // when
        val res = personRepository.findByIdOrNull(_id)

        // then
        res shouldBe expectedRes
    }

}