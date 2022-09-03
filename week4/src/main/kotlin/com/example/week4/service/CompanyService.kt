package com.example.week4.service

import com.example.week4.domain.company.Company
import com.example.week4.domain.company.CompanyNotFoundException
import com.example.week4.domain.company.CompanyRepository
import com.example.week4.domain.company.YMD
import com.example.week4.domain.country.CountryNotFoundException
import com.example.week4.domain.country.CountryRepository
import com.example.week4.domain.person.PersonRepository
import com.example.week4.logger
import com.example.week4.presentation.dto.CompanyRequest
import org.springframework.stereotype.Service
import kotlin.RuntimeException


@Service
class CompanyService(
    private val companyRepository: CompanyRepository,
    private val countryRepository: CountryRepository,
    private val personRepository: PersonRepository
){

    fun findAllCompanyByCountryName(countryName: String): List<String> {
        val countryNameWithUppercase = countryName.uppercase()
        val country = countryRepository.findCountryByName(countryNameWithUppercase)
        logger.info { "country: $country "}
        country ?: throw CountryNotFoundException("Countries named `$countryNameWithUppercase` Could Not be Found")
        val companies = companyRepository.findAllByCountry(country) ?: throw CompanyNotFoundException("Companies under `${country.name}` Could Not be Found")
        if (companies.isEmpty()) throw CompanyNotFoundException("Companies under `${country.name}` Could Not be Found")

        logger.info { "companies: $companies" }
        return companies.map { it.name }
    }

    fun registerCompany(company: CompanyRequest): Long {
        val (companyName: String, foundingDate: YMD, countryName: String) = company

        val countryNullable = countryRepository.findCountryByName(countryName)
        val country = countryNullable ?: throw RuntimeException("There is no Country named as `$countryName`")
        val newCompany = Company(name = companyName, country = country, foundingYMD = foundingDate)

        return companyRepository.findCompanyByNameAndCountry(name = companyName, country = country)?.id ?: return companyRepository.save(newCompany).id
    }

    fun deleteCompany(name: String) {
        val existedCompany = companyRepository.findCompanyByName(name.uppercase())
        logger.info( "existedCompany: $existedCompany" )

        existedCompany?.let {company ->
            personRepository.findPeopleByCompany(company)?.map {person ->
                person.company = null
                personRepository.save(person)
            }

            companyRepository.delete(company)
        }
    }
    private fun uppercaseWithQuestionMark(str: String) = str.uppercase() + "?"



}