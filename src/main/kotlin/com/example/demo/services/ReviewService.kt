package com.example.demo.services

import com.example.demo.datatranferobjects.ReviewDto
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

    fun updateReview(upc: Int, userId: Int, updatedReview: Review): ReviewDto {
        var originalReview = reviewRepo.findByProductAndUser(productRepo.findById(upc).get(), userRepo.findById(userId).get())

        originalReview.rating = updatedReview.rating
        originalReview.reviewTitle = updatedReview.reviewTitle
        originalReview.reviewText = updatedReview.reviewText

        return ReviewDto(reviewRepo.save(originalReview))
    }

    fun saveReview(upc: Int, userId: Int, review: Review): ReviewDto {
        val orders = orderRepo.findAllByUserId(userId)
        val orderProducts = orders.flatMap { it.orderProduct!! }
        val orderedProducts = orderProducts.map { it.product }.filter { it.upc == upc }
        val usersReviewed = orderedProducts.flatMap { it.reviews.map { it.user } }.filter { it?.id == userId }
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
                return ReviewDto(reviewRepo.save(review))
            }
        }
    }
    fun getReviewsByProduct(productId: Int): List<ReviewDto> {
        return reviewRepo.findAllByProduct(productRepo.findById(productId).get()).map { ReviewDto(it) }
    }

    fun getReviewsByUser(userId: Int): List<ReviewDto> {
        return reviewRepo.findAllByUser(userRepo.findById(userId).get()).map { ReviewDto(it) }
    }
}