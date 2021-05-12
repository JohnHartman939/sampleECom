package com.example.demo.repositories

import com.example.demo.entities.Order
import org.springframework.data.repository.CrudRepository

interface OrderRepo: CrudRepository<Order,Int> {
    fun findAllByUserId(id: Int): List<Order>
}