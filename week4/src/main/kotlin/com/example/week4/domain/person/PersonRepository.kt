package com.example.week4.domain.person

import com.example.week4.domain.company.Company
import org.springframework.data.repository.CrudRepository

interface PersonRepository: CrudRepository<Person, Long> {

    fun findPeopleByAgeBetween(age: Int, age2: Int): List<Person>

    fun findPeopleByCompany(company: Company): List<Person>?
}