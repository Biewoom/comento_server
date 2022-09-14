package com.comento.example.presentation.dto

import com.comento.example.OBJECT_MAPPER
import com.comento.example.domain.common.vo.YMD
import com.comento.example.toSingleStringWithoutSpace
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

internal class JpaDtoJsonTest {

    @DisplayName("CompanyDto 직렬화 테스트")
    @ParameterizedTest
    @MethodSource("provider_companyDto")
    fun `test companyDto serialization`(pair: Pair<CompanyDto, String>){
        // given
        val (dto, expectedJsonString) = pair

        // when
        val res = OBJECT_MAPPER.writeValueAsString(dto)

        // then
        res shouldBe expectedJsonString
    }

    companion object {
        @JvmStatic
        fun provider_companyDto() = listOf(
            Pair(
                CompanyDto(
                    name = "GOOGLE",
                    foundingDate = YMD("2022-09-06"),
                    country = "USA"
                ),
                """
                {
                   "name": "GOOGLE",
                   "founding_date": "2022-09-06",
                   "country": "USA"
                }
                """.toSingleStringWithoutSpace()
            ),
            Pair(
                CompanyDto(
                    name = "Apple",
                    foundingDate = YMD("2022-01-22"),
                    country = "USA"
                ),
                """
                {
                    "name": "Apple",
                    "founding_date": "2022-01-22",
                    "country": "USA"
                }
                """.toSingleStringWithoutSpace()
            ),
            Pair(
                CompanyDto(
                    name = "KAKAO",
                    foundingDate = YMD("1999-10-12"),
                    country = "KOREA"
                ),
                """
                {
                    "name": "KAKAO",
                    "founding_date": "1999-10-12",
                    "country": "KOREA"
                }   
                """.toSingleStringWithoutSpace()
            )
        )
    }
}