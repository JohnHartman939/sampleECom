package com.example.demo.controllers

import com.example.demo.datatranferobjects.OrderDto
import com.example.demo.entities.Order
import com.example.demo.services.OrderService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order")
class OrderController( val orderService: OrderService) {
    @PostMapping(params = ["userId"])
    fun saveOrder(@RequestBody orderDto: OrderDto, @RequestParam(value = "userId") userId: Int): OrderDto {
        return orderService.saveOrder(orderDto, userId)
    }

    @GetMapping(params = ["userId"])
    fun getOrdersByUser(@RequestParam(value = "userId") userId: Int): List<OrderDto> {
        return orderService.getOrderByUser(userId)
    }
}