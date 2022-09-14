package com.comento.example.service

import com.comento.example.domain.CompanyNotFoundException
import com.comento.example.domain.common.vo.YMD
import com.comento.example.domain.company.Company
import com.comento.example.domain.company.CompanyRepository
import com.comento.example.logger
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CompanyServiceTest {

    private val companyRepositoryMock = mockk<CompanyRepository>(relaxed = true)
    private val companyServiceSut = CompanyService(companyRepositoryMock)

    private val companyTemporaryList = mutableListOf<Company>()

    @BeforeEach
    fun setUp(){
        companyTemporaryList.clear()

        every {
            companyRepositoryMock.save(capture(companyTemporaryList))
        } returnsArgument 0

        every {
            companyRepositoryMock.findCompaniesByCountry(any())
        } answers {
            val country = arg<String>(0)
            companyTemporaryList.filter { it.country == country }
        }
    }

    @DisplayName("CompanyService findCompanies ByCountryName 찾기")
    @Test
    fun `test findCompaniesByCountryName when success`(){
        // given
        companyRepositoryMock.save( Company(1).apply { foundingDate = YMD("2022-12-07"); name = "JeongSung"; country = "KOREA" } )
        companyRepositoryMock.save( Company(2).apply { foundingDate = YMD("2022-11-11"); name = "TSMC"; country = "TAIWAN" } )
        companyRepositoryMock.save( Company(3).apply { foundingDate = YMD("2022-09-11"); name = "SSOO"; country = "TAIWAN" } )

        val expectedResult = listOf(Company(3).apply { foundingDate = YMD("2022-09-11"); name = "SSOO"; country = "TAIWAN" }, Company(2).apply { foundingDate = YMD("2022-11-11"); name = "TSMC"; country = "TAIWAN" } )
        // when
        val res = companyServiceSut.findCompaniesByCountryName("TAIWAN")

        // then
        res shouldBe expectedResult
    }

    @DisplayName("CompanyService findCompanies ByCountryName 찾을 때, 그 나라 회사가 없는 경우")
    @Test
    fun `test findCompaniesByCountryName when fail`(){
        // given
        companyRepositoryMock.save( Company(2).apply { foundingDate = YMD("2022-11-11"); name = "TSMC"; country = "TAIWAN" } )

        // when
        val ex = shouldThrow<CompanyNotFoundException> {
            companyServiceSut.findCompaniesByCountryName("KOREA")
        }
        // then
        ex.message shouldBe "`KOREA` Was Not Found"
    }
}