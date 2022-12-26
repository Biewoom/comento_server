package com.example.week3

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

internal val logger = KotlinLogging.logger {}

internal typealias SRequestBody = io.swagger.v3.oas.annotations.parameters.RequestBody

@SpringBootApplication
class Week3Application

fun main(args: Array<String>) {
    runApplication<Week3Application>(*args)
}
