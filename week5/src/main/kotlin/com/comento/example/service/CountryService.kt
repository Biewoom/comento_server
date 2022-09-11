package com.comento.example.service

import com.comento.example.domain.CountryNotFoundException
import com.comento.example.domain.country.CountryRepository
import org.springframework.stereotype.Service

@Service
class CountryService(
    private val countryRepository: CountryRepository
) {

    fun findCapitalCityByCountryName(countryName: String): String {
        val country = countryRepository.findCountryByName(countryName.uppercase()) ?: throw CountryNotFoundException("`$countryName` Cannot be Found")
        return country.capitalCity
    }
}