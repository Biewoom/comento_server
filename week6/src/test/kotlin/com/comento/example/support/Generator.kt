package com.comento.example.support

import com.comento.example.domain.person.Person
import com.comento.example.presentation.dto.PersonDto
import com.comento.example.toPrettyJson
import org.junit.jupiter.api.Test

class Generator {

    @Test
    fun generatePersonDtoJsonString(){
        val personDtos = (1..10).map { PersonDto.DYNAMIC_DUMMY }
        println(personDtos.toPrettyJson())
    }

    @Test
    fun generatePerson(){
        val persons = (1..10).map { Person.DYNAMIC_DUMMY }
        println(persons)
    }
}

