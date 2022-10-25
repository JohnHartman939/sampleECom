package com.example.demo.services

import com.example.demo.datatranferobjects.OrderDto
import com.example.demo.datatranferobjects.OrderProductDto2
import com.example.demo.datatranferobjects.ProductDto2
import com.example.demo.entities.Order
import com.example.demo.repositories.OrderProductRepo
import com.example.demo.repositories.OrderRepo
import com.example.demo.repositories.ProductRepo
import com.example.demo.repositories.UserRepo
import org.springframework.stereotype.Service

@Service
class OrderService(val orderRepo: OrderRepo, val userRepo: UserRepo, val orderProductRepo: OrderProductRepo, val productRepo: ProductRepo) {
    fun saveOrder(order: Order): Order {
        return orderRepo.save(order)
    }

    fun getOrderByUser(userId: Int): List<OrderDto> {
        return orderRepo.findByUser_IdUser(userId, OrderDto::class.java)
        //return orderRepo.custom(userId)
    }

    fun getOrderProductDto(userId: Int): List<ProductDto2> {
        return productRepo.findByOrderProduct_Order_User_IdUser(userId)
    }

}