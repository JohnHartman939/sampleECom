package com.example.demo.controllers

import com.example.demo.conversionobjects.ReviewConverter
import com.example.demo.datatranferobjects.ReviewDtoRequest
import com.example.demo.datatranferobjects.ReviewDtoResponse
import com.example.demo.entities.Review
import com.example.demo.services.ReviewService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/review")
class ReviewController(val reviewService: ReviewService, val reviewConverter: ReviewConverter) {

    @PutMapping(params = ["upc", "userId"])
    fun updateReview(@RequestParam("upc") upc: Int, @RequestParam("userId") userId: Int, @RequestBody reviewDtoRequest: ReviewDtoRequest): ReviewDtoResponse {
        return reviewConverter.convertToReviewDtoResponse(reviewService.updateReview(upc, userId, reviewConverter.convertToReview(reviewDtoRequest)))

    }

    @PostMapping(params = ["upc", "userId"])
    fun saveReview(@RequestParam("upc") upc: Int, @RequestParam("userId") userId: Int, @RequestBody reviewDtoRequest: ReviewDtoRequest): ReviewDtoResponse {
        return reviewConverter.convertToReviewDtoResponse(reviewService.saveReview(upc, userId, reviewConverter.convertToReview(reviewDtoRequest)))
    }

    @GetMapping(params = ["userId"])
    fun getReviewsByUser(@RequestParam("userId") userId: Int): List<ReviewDtoResponse> {
        return reviewService.getReviewsByUser(userId).map { reviewConverter.convertToReviewDtoResponse(it) }
    }

    @GetMapping(params = ["upc"])
    fun getReviewsByProduct(@RequestParam("upc") upc: Int): List<ReviewDtoResponse> {
        return reviewService.getReviewsByProduct(upc).map { reviewConverter.convertToReviewDtoResponse(it) }
    }
}