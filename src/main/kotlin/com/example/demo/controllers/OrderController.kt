package com.example.demo.controllers

import com.example.demo.datatranferobjects.*
import com.example.demo.entities.Order
import com.example.demo.errors.CustomConversionException
import com.example.demo.services.OrderService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.core.convert.ConversionService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order")
class OrderController(val orderService: OrderService, @Qualifier("mvcConversionService")val conversionService: ConversionService) {
    @PostMapping(params = ["userId"])
    fun saveOrder(@RequestBody orderDtoRequest: OrderDtoRequest, @RequestParam(value = "userId") userId: Int): OrderDtoResponse {
        orderDtoRequest.userId = userId
        val order = conversionService.convert(orderDtoRequest, Order::class.java ) ?: throw CustomConversionException("There was a problem")
        return conversionService.convert(orderService.saveOrder(order), OrderDtoResponse::class.java )?: throw CustomConversionException("There was a problem")
    }

    @GetMapping(params = ["userId"])
    fun getOrdersByUser(@RequestParam(value = "userId") userId: Int): List<OrderDto> {
        return orderService.getOrderByUser(userId)
    }

    @GetMapping(params = ["userId2"])
    fun getOrderProductDto(@RequestParam(value = "userId2") userId: Int): List<ProductDto2> {
        return orderService.getOrderProductDto(userId)
    }
}