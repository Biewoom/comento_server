package com.comento.example.service

import com.comento.example.domain.CompanyNotFoundException
import com.comento.example.domain.company.Company
import com.comento.example.domain.company.CompanyRepository
import org.springframework.stereotype.Service

@Service
class CompanyService(
    private val companyRepository: CompanyRepository,
) {

    fun findCompaniesByCountryName(countryName: String): List<Company> {
        val companies = companyRepository.findCompaniesByCountry(countryName.uppercase())
        if ( companies.isEmpty() ) throw CompanyNotFoundException("`$countryName` Was Not Found")

        return companies.sortedBy { it.foundingDate }
    }

}