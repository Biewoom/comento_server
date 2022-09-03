package com.example.week4.presentation.dto

import com.example.week4.domain.company.YMD
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
data class CompanyRequest (
    val name: String,
    val foundingDate: YMD,
    val country: String
)
