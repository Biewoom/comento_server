package com.example.week4.domain.company

data class CompanyNotFoundException(override val message: String): RuntimeException(message)