package com.example.demo.services

import com.example.demo.datatranferobjects.OrderDto
import com.example.demo.datatranferobjects.OrderDtoResponse
import com.example.demo.entities.Order
import com.example.demo.entities.User
import com.example.demo.repositories.OrderRepo
import com.example.demo.repositories.UserRepo
import org.springframework.stereotype.Service

@Service
class OrderService(val orderRepo: OrderRepo, val userRepo: UserRepo) {
    fun saveOrder(order: Order): Order {
        return orderRepo.save(order)
    }

    fun getOrderByUser(userId: Int): List<OrderDto> {
        return orderRepo.custom(userId)
    }

}