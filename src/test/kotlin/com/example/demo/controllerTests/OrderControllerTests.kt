package com.example.demo.controllerTests

import com.example.demo.controllers.OrderController
import com.example.demo.services.OrderService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(SpringRunner::class)
class OrderControllerTests(var orderController: OrderController) {

    lateinit var orderService: OrderService

    @Test
    fun givenAnOrderIsPlaced_thenCheckOrderIsReturned() {

    }


}