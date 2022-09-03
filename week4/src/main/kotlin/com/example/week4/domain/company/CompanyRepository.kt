package com.example.week4.domain.company

import com.example.week4.domain.country.Country
import org.springframework.data.repository.CrudRepository

interface CompanyRepository: CrudRepository<Company, Long>{

    fun findAllByCountry(country: Country): List<Company>?

    fun findCompanyByNameAndCountry(name: String, country: Country): Company?
    fun findCompanyByName(name: String): Company?
}