package com.example.demo.services

import com.example.demo.datatranferobjects.ReviewDto
import com.example.demo.entities.Order
import com.example.demo.entities.Review
import com.example.demo.errors.AlreadyReviewedException
import com.example.demo.errors.NotOrderedException
import com.example.demo.repositories.OrderRepo
import com.example.demo.repositories.ProductRepo
import com.example.demo.repositories.ReviewRepo
import com.example.demo.repositories.UserRepo
import org.springframework.stereotype.Service

@Service
class ReviewService( val reviewRepo: ReviewRepo, val userRepo: UserRepo, val productRepo: ProductRepo, val orderRepo: OrderRepo) {

    fun updateReview(upc: Int, userId: Int, updatedReview: Review): Review {
        val originalReview = reviewRepo.findByProductAndUser(productRepo.findById(upc).get(), userRepo.findById(userId).get())

        originalReview.rating = updatedReview.rating
        originalReview.reviewName = updatedReview.reviewName
        originalReview.reviewText = updatedReview.reviewText

        return reviewRepo.save(originalReview)
    }

    fun saveReview(upc: Int, userId: Int, review: Review): Review {
        val orders = orderRepo.findByUser_IdUser(userId, Order::class.java)
        val orderProducts = orders.flatMap { it.orderProduct }
        val orderedProducts = orderProducts.map { it.product }.filter { it.upc == upc }
        val usersReviewed = orderedProducts.flatMap { product -> product.reviews.map { it.user } }.filter { it.idUser == userId }
        when {
            orderedProducts.isEmpty() -> {
                throw NotOrderedException("The User did not order this product")
            }
            usersReviewed.isNotEmpty() -> {
                throw AlreadyReviewedException("The user already reviewed this item")
            }
            else -> {
                review.product = productRepo.findById(upc).get()
                review.user = userRepo.findById(userId).get()
                return reviewRepo.save(review)
            }
        }
    }
    fun getReviewsByProduct(productId: Int): List<ReviewDto> {
        return reviewRepo.findAllByProduct(productRepo.findById(productId).get())
    }

    fun getReviewsByUser(userId: Int): List<ReviewDto> {
        return reviewRepo.findAllByUser(userRepo.findById(userId).get())
    }
}