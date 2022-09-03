package com.example.week4.presentation.controller

import com.example.week4.domain.company.CompanyNotFoundException
import com.example.week4.domain.country.CountryNotFoundException
import com.example.week4.presentation.dto.CompanyRequest
import com.example.week4.service.CompanyService
import org.slf4j.MDC
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/company")
class CompanyController(
    private val companyService: CompanyService
) {

    @GetMapping("/country/{countryName}")
    fun getCompanyNamesByCountry(@PathVariable("countryName") countryName: String): ResponseEntity<*> = runCatching {
        MDC.clear()
        MDC.put("requestId", UUID.randomUUID().toString())
        companyService.findAllCompanyByCountryName(countryName)
    }.mapCatching { companies ->
        ResponseEntity.status(HttpStatus.OK).body(companies)
    }.recoverCatching { e ->
        when (e) {
            is CountryNotFoundException -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
            is CompanyNotFoundException -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
            else -> throw e
        }
    }.onFailure {e ->
        e.printStackTrace()
    }.getOrNull() ?: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("UNKNOWN ERROR")


    @DeleteMapping("/{companyName}")
    fun deleteCompanyById(@PathVariable("companyName") name: String){
        return companyService.deleteCompany(name)
    }

    @PostMapping
    fun registerNewCompany(@RequestBody company: CompanyRequest): Long? {
        return companyService.registerCompany(company)
    }

}