package com.comento.example

import com.comento.example.domain.person.Person
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import kotlin.math.pow
import kotlin.math.roundToLong


internal fun String.toNumber(): Number = toLongOrNull() ?: toDoubleOrNull() ?: 0
internal fun Double.toRound(int: Int) = (this * (10.0.pow(int))).roundToLong() / ( 10.0.pow(int) )

internal typealias SRequestBody = io.swagger.v3.oas.annotations.parameters.RequestBody

val logger = KotlinLogging.logger { }
val OBJECT_MAPPER = jacksonObjectMapper()

fun Any.toJson() = OBJECT_MAPPER.writeValueAsString(this)
fun Any.toPrettyJson() = OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(this)

@SpringBootApplication
class Application
fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
