package com.comento.example.utils

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class StringUtilsTest {

    @DisplayName("String 두 개 max로 비교했을 때, 알파벳 순서가 빠른 거 반환 - case 1")
    @Test
    fun `test string max`(){
        // given
        val str1 = "Tom"
        val str2 = "Alice"
        val expected = "Alice"

        // when
        val res = max(str1, str2)

        // then
        res shouldBe expected
    }

    @DisplayName("String 두 개 max로 비교했을 때, 알파벳 순서가 빠른 거 반환 - case 2")
    @Test
    fun `test string max 2`(){
        // given
        val str1 = "elephant"
        val str2 = "fox"
        val expected = "elephant"

        // when
        val res = max(str1, str2)

        // then
        res shouldBe expected
    }
}