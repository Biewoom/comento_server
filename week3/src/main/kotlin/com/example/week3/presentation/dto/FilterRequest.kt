package com.example.week3.presentation.dto

data class FilterRequest(
    val ageCutoff: Int?,
    val except: List<String>?,
    val heightCutoff: Int?,
    val persons: List<Person>
)