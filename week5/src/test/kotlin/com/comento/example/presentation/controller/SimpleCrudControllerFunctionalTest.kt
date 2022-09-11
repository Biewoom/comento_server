package com.comento.example.presentation.controller

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.annotation.DirtiesContext.ClassMode
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@AutoConfigureTestDatabase(replace = Replace.ANY)
internal class SimpleCrudControllerFunctionalTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @DisplayName("Http GET method로 Simple CRUD KOREA 수도 가져오는 실행, 통합환경에서도 정상동작 확인")
    @Test
    fun `test Simple CRUD, find capitalCity`() {
        // given
        val countryName = "KOREA"
        val capitalCity = "Seoul"

        // when
        mockMvc.get("${SimpleCrudControllerWebMvcTest.BASE_URL}/countries/$countryName/capital-city"){
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

    companion object {
        const val BASE_URL = "/api/v1/simple-crud"
    }

}