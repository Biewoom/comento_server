package com.comento.example.service

import com.comento.example.domain.common.enums.Gender
import com.comento.example.domain.person.Person
import com.comento.example.domain.person.PersonRepository
import com.comento.example.presentation.dto.PersonDto
import com.comento.example.callPrivateFunc
import com.comento.example.toArgumentsStream
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import io.mockk.spyk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

internal class PersonServicePrivateMethodTest {

    private val personRepositoryMock = mockk<PersonRepository>(relaxed = true)
    private val personRepositorySut = PersonService(personRepositoryMock)

    @DisplayName("PersonService Spy ConvertToPerson private method 테스트")
    @ParameterizedTest
    @MethodSource("provider_success_case")
    fun `test PersonService ConvertToPerson when success`(pair: Pair<PersonDto, Person>){
        // given
        val ( personDto, person ) = pair

        // when
        val res = personRepositorySut.callPrivateFunc("convertToPerson", personDto)

        // then
        res shouldBe person
    }


    companion object {
        @JvmStatic
        fun provider_success_case() = listOf(
            Pair(
                PersonDto(
                    personId = null,
                    age = 17,
                    height = null,
                    weight = 105,
                    name = "에철민",
                    country = "ENGLAND",
                    company = null,
                    gender = Gender.UNKNOWN,
                    isMarried = true
                ),
                Person (
                    name = "에철민",
                    country = "ENGLAND",
                    gender = Gender.UNKNOWN,
                ).apply {
                    age = 17
                    height = null
                    weight = 105
                    company = null
                    isMarried = true
                } // Output
            ),
        ).toArgumentsStream()
    }
}