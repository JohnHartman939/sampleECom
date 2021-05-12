package com.example.demo.services

import com.example.demo.conversionobjects.OrderConversion
import com.example.demo.conversionobjects.OrderConverter
import com.example.demo.datatranferobjects.OrderDto
import com.example.demo.repositories.OrderRepo
import com.example.demo.repositories.UserRepo
import org.springframework.stereotype.Service

@Service
class OrderService(val orderRepo: OrderRepo, val userRepo: UserRepo, val orderConverter: OrderConverter) {
    fun saveOrder(orderDto: OrderDto, userId: Int): OrderDto {
        var order = orderRepo.save(orderConverter.convertToOrder(OrderConversion(orderDto, userId)))
        return orderConverter.convertToOrderDto(OrderConversion(order))
    }

    fun getOrderByUser(userId: Int): List<OrderDto> {
        return orderRepo.findAllByUserId(userId).map { orderConverter.convertToOrderDto(OrderConversion(it)) }
    }

}