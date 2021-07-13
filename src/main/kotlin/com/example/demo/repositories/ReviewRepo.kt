package com.example.demo.repositories

import com.example.demo.entities.Product
import com.example.demo.entities.Review
import com.example.demo.entities.User
import org.springframework.core.annotation.Order
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ReviewRepo: CrudRepository<Review, Int> {
    fun findAllByUser(user: User): List<Review>
    fun findAllByProduct(product: Product): List<Review>
    fun findByProductAndUser(product: Product, user: User): Review
}