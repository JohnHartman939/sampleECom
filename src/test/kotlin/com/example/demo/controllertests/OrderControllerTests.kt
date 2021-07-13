package com.example.demo.controllertests

import com.example.demo.controllers.OrderController
import com.example.demo.services.OrderService
import com.ninjasquad.springmockk.MockkBean
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(SpringRunner::class)
class OrderControllerTests(var orderController: OrderController) {

    @MockkBean
    lateinit var orderService: OrderService

    @Test
    fun givenAnOrderIsPlaced_thenCheckOrderIsReturned() {

    }


}