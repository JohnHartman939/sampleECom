package com.example.demo.services

import com.example.demo.entities.Order
import com.example.demo.repositories.OrderRepo
import com.example.demo.repositories.UserRepo
import org.springframework.stereotype.Service

@Service
class OrderService(val orderRepo: OrderRepo, val userRepo: UserRepo) {
    fun saveOrder(order: Order): Order {
        return orderRepo.save(order)
    }

    fun getOrderByUser(userId: Int): List<Order> {
        return orderRepo.findAllByUser(userRepo.findByIdUser(userId))
    }

}