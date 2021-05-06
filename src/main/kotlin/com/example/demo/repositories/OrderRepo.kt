package com.example.demo.repositories

import com.example.demo.entities.Order
import com.example.demo.entities.OrderKey
import org.springframework.data.repository.CrudRepository

interface OrderRepo: CrudRepository<Order,OrderKey> {
}