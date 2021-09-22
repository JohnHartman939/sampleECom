package com.example.demo.controllerTests

import com.example.demo.testData.Data
import com.example.demo.testData.DataHelper
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class UserControllerTests: BaseControllerTests() {
    var postRequestBuilder: MockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/user/register")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)

    var getRequestBuilder: MockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/user/")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)

    val userDtoString: String = DataHelper.dtoToJsonString(Data.getUserDto())

    @Test
    @Order(1)
    fun `test saving a user`() {

        mockMvc.perform(postRequestBuilder.content(userDtoString))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string(userDtoString))
    }

    @Test
    @Order(2)
    fun `test get user by email`() {
        mockMvc.perform(getRequestBuilder.queryParam("email", "test@gmail.com"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string(userDtoString))
    }

    @Test
    @Order(3)
    fun `test get user by id`() {
        mockMvc.perform(getRequestBuilder.queryParam("id", "1"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string(userDtoString))
    }
}