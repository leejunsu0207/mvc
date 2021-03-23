package com.example.mvc.controller.exception

import com.example.mvc.model.http.UserRequest
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.util.LinkedMultiValueMap

@WebMvcTest
@AutoConfigureMockMvc
class ExceptionApiControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun helloTest(){
        mockMvc.perform(
            get("/api/exception/hello")
        ).andExpect(
            status().`is`(200)
        ).andExpect(
            content().string("hello")
        ).andDo(print())
    }

    @Test
    fun getTest(){
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name","lee")
        queryParams.add("age","22")

        mockMvc.perform(
            get("/api/exception").queryParams(queryParams)
        ).andExpect(
            status().isOk
        ).andExpect(
            content().string("lee 22")
        ).andDo(
            print()
        )
    }

    @Test
    fun getFailTest(){
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name","lee")
        queryParams.add("age","9")

        mockMvc.perform(
            get("/api/exception").queryParams(queryParams)
        ).andExpect(
            status().isBadRequest
        ).andExpect(
            content().contentType("application/json")
        ).andExpect(
            jsonPath("\$.result_code").value("FAIL")
        ).andExpect(
            jsonPath("\$.errors[0].field").value("age")
        ).andExpect(
            jsonPath("\$.errors[0].value").value("9")
        ).andDo(
            print()
        )
    }

    @Test
    fun postTest(){

        val userRequest = UserRequest().apply {
            this.name  = "lee"
            this.age = 10
            this.phoneNumber = "010-5555-5555"
            this.address = "경기도 고양시"
            this.email = "test@gmail.com"
            this.createdAt = "2021-03-23 10:39:30"
        }
        val json = jacksonObjectMapper().writeValueAsString(userRequest)
        println(json)


        mockMvc.perform(
            post("/api/exception")
                .content(json)
                .contentType("application/json")
                .accept("application/json")
        ).andExpect(
            status().isOk
        ).andExpect(
            jsonPath("\$.name").value("lee")
        ).andExpect(
            jsonPath("\$.age").value("10")
        ).andExpect(
            jsonPath("\$.phoneNumber").value("010-5555-5555")
        ).andExpect(
            jsonPath("\$.address").value("경기도 고양시")
        ).andExpect(
            jsonPath("\$.email").value("test@gmail.com")
        ).andExpect(
            jsonPath("\$.createdAt").value("2021-03-23 10:39:30")
        ).andDo(print())
    }

    @Test
    fun postFailTest(){

        val userRequest = UserRequest().apply {
            this.name  = "lee"
            this.age = -1
            this.phoneNumber = "010-5555-5555"
            this.address = "경기도 고양시"
            this.email = "test@gmail.com"
            this.createdAt = "2021-03-23 10:39:30"
        }
        val json = jacksonObjectMapper().writeValueAsString(userRequest)
        println(json)


        mockMvc.perform(
            post("/api/exception")
                .content(json)
                .contentType("application/json")
                .accept("application/json")
        ).andExpect(
            status().`is`(400)
        ).andDo(print())
    }

}