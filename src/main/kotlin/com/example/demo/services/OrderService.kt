package com.example.demo.services

import com.example.demo.conversionobjects.OrderConversion
import com.example.demo.conversionobjects.OrderConverter
import com.example.demo.datatranferobjects.OrderDto
import com.example.demo.repositories.OrderRepo
import org.springframework.stereotype.Service

@Service
class OrderService(val orderRepo: OrderRepo, val orderConverter: OrderConverter) {
    fun saveOrder(orderDto: OrderDto, userId: Int): OrderDto {
        var order = orderConverter.convertToOrder(OrderConversion(orderDto, userId))
        order.orderSum = order.orderProduct!!.sumByDouble { it.quantity * it.product.price  }
        orderRepo.save(order)
        println("order total" + order.orderSum)
        return orderConverter.convertToOrderDto(OrderConversion(order))
    }

    fun getOrderByUser(userId: Int): List<OrderDto> {
        return orderRepo.findAllByUserId(userId).map { orderConverter.convertToOrderDto(OrderConversion(it)) }
    }

}