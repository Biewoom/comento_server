package com.comento.example

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.provider.Arguments
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import java.util.stream.Stream
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.jvm.isAccessible

internal fun List<Any?>.toArgumentsStream() = Stream.of(*this.map{ Arguments.of (it) }.toTypedArray())
inline fun <reified T> T.callPrivateFunc(name: String, vararg args: Any?): Any? =
	T::class
		.declaredMemberFunctions
		.firstOrNull { it.name == name }
		?.apply { isAccessible = true }
		?.call(this, *args)
internal fun String.toSingleStringWithoutSpace() = this.trimIndent().replace("\n", "").replace(" ", "")

@SpringBootTest
internal class ApplicationTests {

	@Test
	fun contextLoads() {
	}

}

