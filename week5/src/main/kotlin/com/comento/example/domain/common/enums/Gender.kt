package com.comento.example.domain.common.enums

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import javax.persistence.AttributeConverter
import javax.persistence.Converter

enum class Gender(@JsonValue val genderStr: String, val magicNumber: Int) {
    UNKNOWN("UnKnown", 0),
    MALE("Male", 1),
    FEMALE("Female", 2);

    companion object {
        @JsonCreator
        operator fun invoke(genderStr: String?) =  values().find { it.genderStr === genderStr } ?: UNKNOWN
    }

    @Converter
    class GenderConverter: AttributeConverter<Gender, Int>{
        override fun convertToDatabaseColumn(attribute: Gender?): Int = attribute?.magicNumber ?: 0

        override fun convertToEntityAttribute(dbData: Int?): Gender  = dbData?.let {
                values().find { gender -> gender.magicNumber == it }
            } ?: UNKNOWN
    }
}
