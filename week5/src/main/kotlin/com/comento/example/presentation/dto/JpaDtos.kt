package com.comento.example.presentation.dto

import com.comento.example.domain.common.enums.Gender
import com.comento.example.domain.common.vo.YMD
import com.comento.example.domain.person.Person
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import io.swagger.v3.oas.annotations.media.Schema

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
@Schema(title = "회사 DTO")
data class CompanyDto (
    @Schema(title = "회사 이름", example = "Apple") val name: String,
    @Schema(title = "회사 설립 연도", example = "2011-01-22")  val foundingDate: YMD,
    @Schema(title = "국가", example = "USA") val country: String
)

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
@Schema(title = "BlindDate DTO")
data class BlindDateDto (
    @Schema(title = "사람 이름", example = "Tom") val name: String,
    @Schema(title = "성별", example = "Male") val gender: Gender,
    @Schema(title = "나이", example = "45") val age: Int,
    @Schema(title = "키", example = "175") val height: Int?,
    @Schema(title = "몸무게", example = "73") val weight: Int?,
    @Schema(title = "회사 이름", example = "Apple") val company: String?,
    @Schema(title = "나라 이름", example = "KOREA") val country: String
){
    companion object {
        fun fromPerson(person: Person) = BlindDateDto(
            name = person.name,
            gender = person.gender,
            age = person.age ?: 0,
            height = person.height,
            weight = person.weight,
            company = person.company,
            country = person.country
        )
    }
}
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
@Schema(title = "Person DTO")
data class PersonDto (
    @Schema(title = "Person Id", example = "10") val personId: Int?,
    @Schema(title = "나이", example = "45") val age: Int?,
    @Schema(title = "키", example = "175") val height: Int?,
    @Schema(title = "몸무게", example = "73") val weight: Int?,
    @Schema(title = "사람 이름", example = "Tom") val name: String,
    @Schema(title = "성별", example = "Male") val gender: Gender?,
    @Schema(title = "결혼 여부", example = "true") val isMarried: Boolean?,
    @Schema(title = "회사 이름", example = "Apple") val company: String?,
    @Schema(title = "나라 이름", example = "KOREA") val country: String
){
    companion object
}

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ResultDto(
    @Schema(title = "결과 타입들", example = "[1,0,1]" ) val resultTypes: List<Int>,
    @Schema(title = "Person Id들", example = "[10, 1, 49, 3]")  val personIds: List<Int>
)

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
data class CompanyRequest (
    val name: String,
    val foundingDate: YMD,
    val country: String
)
