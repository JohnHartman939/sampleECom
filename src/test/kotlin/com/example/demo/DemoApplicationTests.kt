package com.example.demo

import com.example.demo.datatranferobjects.ProductDto
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post



//@SpringBootTest
//@ActiveProfiles("test")
//@AutoConfigureMockMvc
//class DemoApplicationTests() {
//
//	@Autowired
//	lateinit var mockMvc: MockMvc
//
//	@Autowired
//	lateinit var objectMapper: ObjectMapper
//
//	val objectWriter: ObjectWriter by lazy { objectMapper.writer() }
//
//
//	@Test
//	fun contextLoads() {
//	}

//	@Test
//	fun testSavingAProduct(){
//		val productDto = ProductDto(1,"anvil", "roadrunner", 5.65)
//		val productDtoString = objectWriter.writeValueAsString(productDto)
//
//		print(productDtoString)
//		mockMvc.post("/products") {
//			contentType = MediaType.APPLICATION_JSON
//			content = productDtoString
//			accept = MediaType.APPLICATION_JSON
//		}.andExpect {
//			status { isOk() }
//			content { contentType(MediaType.APPLICATION_JSON) }
//			content { json(productDtoString)}
//		}
//	}

//    @Test
//    fun testGetAProductByUpc() {
//        val productDto = ProductDto(1, "anvil", "roadrunner", 5.65)
//        val productDtoString = objectWriter.writeValueAsString(productDto)
//        val multiValueMap = linkedMapOf("upc" to "1")
//
//        mockMvc.post("/products") {
//            contentType = MediaType.APPLICATION_JSON
//            content = productDtoString
//            accept = MediaType.APPLICATION_JSON
//        }.andExpect {
//            status { isOk() }
//            content { contentType(MediaType.APPLICATION_JSON) }
//            content { json(productDtoString) }
//        }
//
//            mockMvc.get("/products") {
//                contentType = MediaType.APPLICATION_JSON
//                accept = MediaType.APPLICATION_JSON
//                param("upc", "1")
//
//            }.andExpect {
//                status { isOk() }
//                content { contentType(MediaType.APPLICATION_JSON) }
//                content { json(productDtoString) }
//            }
//        }
//
//    @Test
//    fun testGetAProductByPrice() {
//        val productDto = ProductDto(1, "anvil", "roadrunner", 5.65)
//        var productDtoString = objectWriter.writeValueAsString(productDto)
//        val multiValueMap = linkedMapOf("upc" to "1")
//
//        mockMvc.post("/products") {
//            contentType = MediaType.APPLICATION_JSON
//            content = productDtoString
//            accept = MediaType.APPLICATION_JSON
//        }.andExpect {
//            status { isOk() }
//            content { contentType(MediaType.APPLICATION_JSON) }
//            content { json(productDtoString) }
//        }
//
//        productDtoString = "[$productDtoString]"
//
//
//        mockMvc.get("/products") {
//            contentType = MediaType.APPLICATION_JSON
//            accept = MediaType.APPLICATION_JSON
//            param("low", "5")
//            param("high", "6")
//
//        }.andExpect {
//            status { isOk() }
//            content { contentType(MediaType.APPLICATION_JSON) }
//            content { json(productDtoString) }
//        }
//    }
//
//}
