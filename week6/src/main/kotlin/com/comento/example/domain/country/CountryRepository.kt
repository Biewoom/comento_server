package com.comento.example.domain.country

import org.springframework.data.repository.CrudRepository

interface CountryRepository: CrudRepository<Country, Long> {

    fun findCountryByName(name: String): Country?
}