package com.comento.example.domain

data class CompanyNotFoundException(override val message: String): RuntimeException(message)
data class CountryNotFoundException(override val message: String): RuntimeException(message)
data class PersonNotFoundException(override val message: String): RuntimeException(message)
data class BlindDateNotFoundException(override val message: String): RuntimeException(message)