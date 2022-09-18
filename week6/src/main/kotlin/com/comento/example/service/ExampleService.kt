package com.comento.example.service

import com.comento.example.domain.CountryNotFoundException
import com.comento.example.domain.PersonNotFoundException
import com.comento.example.domain.common.enums.Gender
import com.comento.example.domain.country.CountryRepository
import com.comento.example.domain.person.PersonRepository
import com.comento.example.logger
import org.springframework.stereotype.Service

@Service
class ExampleService(
    private val countryRepository: CountryRepository,
    private val personRepository: PersonRepository
) {

    fun getAverageOfHeightOfPersonsInCountryName(countryName: String, gender: Gender): Double {
        val country = countryRepository.findCountryByName(countryName)
            ?: throw CountryNotFoundException("`$countryName` cannot found")

        val people = personRepository.findPeopleByCountryAndGenderAndHeightNotNull(country, gender).ifEmpty { null }
            ?: throw PersonNotFoundException("There is No $Gender Persons In $countryName`")

        logger.info { "Hello" }
        logger.info { "first country: ${people.first().country}" }
        logger.info { "first company: ${people.first().company}" }
        return people.map { it.height ?: throw IllegalStateException("Height Cannot be Null") }
                    .average()
    }
}