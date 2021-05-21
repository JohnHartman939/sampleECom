package com.example.demo.controllers

import com.example.demo.conversionobjects.OrderConverter
import com.example.demo.datatranferobjects.OrderDtoRequest
import com.example.demo.datatranferobjects.OrderDtoResponse
import com.example.demo.services.OrderService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order")
class OrderController( val orderService: OrderService, val orderConverter: OrderConverter) {
    @PostMapping(params = ["userId"])
    fun saveOrder(@RequestBody orderDtoRequest: OrderDtoRequest, @RequestParam(value = "userId") userId: Int): OrderDtoResponse {
        return orderConverter.convertToOrderResponseDto(orderService.saveOrder(orderConverter.convertToOrder(orderDtoRequest, userId)))
    }

    @GetMapping(params = ["userId"])
    fun getOrdersByUser(@RequestParam(value = "userId") userId: Int): List<OrderDtoResponse> {
        return orderService.getOrderByUser(userId).map { orderConverter.convertToOrderResponseDto(it) }
    }
}