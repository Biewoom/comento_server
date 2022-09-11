package com.comento.example.service

import com.comento.example.domain.PersonNotFoundException
import com.comento.example.domain.common.enums.Gender
import com.comento.example.domain.person.Person
import com.comento.example.domain.person.PersonRepository
import com.comento.example.support.DYNAMIC_DUMMY
import com.comento.example.toArgumentsStream
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

internal class PersonServiceTest {

    private val personRepositoryMock = mockk<PersonRepository>(relaxed = true)
    private val personServiceSut = PersonService(personRepositoryMock)

    @DisplayName("findBlindDateCoupleList 성공 케이스 테스트")
    @Test
    fun `test findBlindDateCoupleList when success`(){
        // given
        every {
            personRepositoryMock.findPeopleByGenderAndAgeNotNull(Gender.MALE)
        } returns listOf(
            Person.DYNAMIC_DUMMY.copy(gender = Gender.MALE).apply { age = 10 },
            Person.DYNAMIC_DUMMY.copy(gender = Gender.MALE).apply { age = 15 },
            Person.DYNAMIC_DUMMY.copy(gender = Gender.MALE).apply { age = 20 }
        )
        every {
            personRepositoryMock.findPeopleByGenderAndAgeNotNull(Gender.FEMALE)
        } returns listOf(
            Person.DYNAMIC_DUMMY.copy(gender = Gender.FEMALE).apply { age = 12 },
            Person.DYNAMIC_DUMMY.copy(gender = Gender.FEMALE).apply { age = 16 },
            Person.DYNAMIC_DUMMY.copy(gender = Gender.FEMALE).apply { age = 23 }
        )
        val expectedSize = 4
        // when
        val res = personServiceSut.findBlindDateCoupleList(3)
        // then
        res.size shouldBe expectedSize
    }

    @DisplayName("findBlindDateCoupleList 에러 케이스 테스트")
    @ParameterizedTest
    @MethodSource("provider_error_case")
    fun `test findBLinDateCoupleList when error`(pair: Pair<Gender, String>){
        val (gender, expectedMessage) = pair

        every {
           personRepositoryMock.findPeopleByGenderAndAgeNotNull(gender)
        } returns emptyList()

        val ex = shouldThrow<PersonNotFoundException> {
            personServiceSut.findBlindDateCoupleList(4)
        }

        ex.message shouldBe expectedMessage
    }

    companion object {
        @JvmStatic
        fun provider_error_case() = listOf(
            Pair(Gender.MALE, "men cannot be found"),
            Pair(Gender.FEMALE,"women cannot be found"),
        ).toArgumentsStream()
    }
}
