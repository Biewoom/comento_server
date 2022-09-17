package com.comento.example.utils

import com.comento.example.toNumber
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beInstanceOf
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class StringExtensionFunctionTest {

    @DisplayName("String 확장함수 toNumber 실행시, 정수형은 Long type으로 변환" )
    @Test
    fun `test toNumber case Long`(){
        // given
        val case = "100"

        // when
        val res = case.toNumber()

        // then
        res should beInstanceOf<Long>() // Long Type
        res shouldBe 100L

    }

    @DisplayName("String 확장함수 toNumber 실행시, 소숫점은 Double type으로 변환")
    @Test
    fun `test toNumber case Floating point`(){
        // given
        val caseWithFloatingpoint = "10.55"

        // when
        val res = caseWithFloatingpoint.toNumber()

        // then
        res should beInstanceOf<Double>()
        res shouldBe 10.55
    }

    @DisplayName("String 확장함수 toNumber 실행시, 정수형/소숫점이 아닐 시 0을 반환")
    @Test
    fun `test toNumber case not Number`(){
        // given
        val caseNotNumber = "abc"

        // when
        val res = caseNotNumber.toNumber()

        // then
        res shouldBe 0
    }
}