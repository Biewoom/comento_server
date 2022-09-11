package com.comento.example.presentation.controller

import com.comento.example.domain.CountryNotFoundException
import com.comento.example.presentation.SimpleCrudController
import com.comento.example.service.CompanyService
import com.comento.example.service.CountryService
import com.comento.example.service.PersonService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(controllers = [SimpleCrudController::class])
internal class SimpleCrudControllerWebMvcTest {

    @MockkBean(relaxed = true)
    private lateinit var companyService: CompanyService

    @MockkBean(relaxed = true)
    private lateinit var countryService: CountryService

    @MockkBean(relaxed = true)
    private lateinit var personService: PersonService

    @Autowired
    private lateinit var mockMvc: MockMvc

    @DisplayName("Http GET method로 Simple CRUD url로 KOREA 수도 가져오는 로직 정상동작 확인")
    @Test
    fun `test Simple CRUD, find capitalCity`() {
        // given
        val countryName = "KOREA"
        val capitalCity = "Seoul"
        every {
            countryService.findCapitalCityByCountryName(countryName)
        } returns capitalCity

        // when
        mockMvc.get("$BASE_URL/countries/$countryName/capital-city"){
            accept = MediaType.APPLICATION_JSON
            }
            .andDo { println() }

        // then
            .andExpect {
                status { isOk() }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                    string(capitalCity)
                }
            }
    }

    @DisplayName("Http GET method로 Simple-crud capital city 실패 했을 때, NOT FOUND 확인")
    @Test
    fun `test Simple CRUD, find capitalCity when fail`() {
        // given
        val countryName = "KOREA"
        val errorMessage = "`$countryName` Cannot be Found"
        every {
            countryService.findCapitalCityByCountryName(countryName)
        } throws CountryNotFoundException("`$countryName` Cannot be Found")

        // when
        mockMvc.get("$BASE_URL/countries/$countryName/capital-city"){
            accept = MediaType.APPLICATION_JSON
        }
            .andDo { println() }

            // then
            .andExpect {
                status { isNotFound() }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                    string(errorMessage)
                }
            }
    }


    companion object {
        const val BASE_URL = "/api/v1/simple-crud"
    }

}