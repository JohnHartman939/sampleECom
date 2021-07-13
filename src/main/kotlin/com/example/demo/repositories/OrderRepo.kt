package com.example.demo.repositories

import com.example.demo.entities.Order
import com.example.demo.entities.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepo: CrudRepository<Order,Int> {
    fun findAllByUser(user: User): List<Order>
}