package com.example.week4

import mu.KLogger
import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class Week4Application


val logger: KLogger = KotlinLogging.logger {}

fun main(args: Array<String>) {
    runApplication<Week4Application>(*args)
}
