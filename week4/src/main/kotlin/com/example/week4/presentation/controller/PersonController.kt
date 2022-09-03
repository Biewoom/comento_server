package com.example.week4.presentation.controller

import com.example.week4.service.PersonService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/person")
class PersonController(
    private val personService: PersonService
){

}