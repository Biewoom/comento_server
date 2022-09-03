package com.example.week4.domain.country

import org.springframework.data.repository.CrudRepository

interface CountryRepository: CrudRepository<Country, Long>{

    fun findCountryByName(name: String): Country?
}