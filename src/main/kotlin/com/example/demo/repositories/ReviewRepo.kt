package com.example.demo.repositories

import com.example.demo.datatranferobjects.ReviewDto
import com.example.demo.entities.Product
import com.example.demo.entities.Review
import com.example.demo.entities.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface ReviewRepo: CrudRepository<Review, Int> {
    fun findAllByUser(user: User): List<ReviewDto>
    fun findAllByProduct(product: Product): List<ReviewDto>
    fun findByProductAndUser(product: Product, user: User): Review
}

