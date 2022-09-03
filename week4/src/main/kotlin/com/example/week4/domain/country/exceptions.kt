package com.example.week4.domain.country


data class CountryNotFoundException(override val message: String): RuntimeException(message)