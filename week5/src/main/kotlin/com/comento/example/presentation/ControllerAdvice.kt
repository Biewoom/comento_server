package com.comento.example.presentation

import com.comento.example.domain.CompanyNotFoundException
import com.comento.example.domain.CountryNotFoundException
import com.comento.example.logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController

@RestController
@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(CompanyNotFoundException::class, CountryNotFoundException::class)
    fun handleIllegalStateException(ex: Exception): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(RuntimeException::class, Exception::class)
    fun handleUnknownException(ex: Exception): ResponseEntity<String> {
        logger.error { "UnknownException Occur: ${ex.message}"}
        return ResponseEntity(ex.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}