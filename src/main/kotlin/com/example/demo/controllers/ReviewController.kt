package com.example.demo.controllers

import com.example.demo.conversionobjects.ReviewConverter
import com.example.demo.datatranferobjects.ReviewDto
import com.example.demo.services.ReviewService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/review")
class ReviewController(val reviewService: ReviewService, val reviewConverter: ReviewConverter) {

    @PutMapping(params = ["upc", "userId"])
    fun updateReview(@RequestParam("upc") upc: Int, @RequestParam("userId") userId: Int, @RequestBody reviewDto: ReviewDto): ReviewDto {
        return reviewConverter.convertToReviewDto(reviewService.updateReview(upc, userId, reviewConverter.convertToReview(reviewDto, upc, userId)))

    }

    @PostMapping(params = ["upc", "userId"])
    fun saveReview(@RequestParam("upc") upc: Int, @RequestParam("userId") userId: Int, @RequestBody reviewDtoRequest: ReviewDto): ReviewDto {
        return reviewConverter.convertToReviewDto(reviewService.saveReview(
            upc = upc,
            userId = userId,
            review = reviewConverter.convertToReview(reviewDto = reviewDtoRequest, upc = upc, userId = userId)
        ))
    }

    @GetMapping(params = ["userId"])
    fun getReviewsByUser(@RequestParam("userId") userId: Int): List<ReviewDto> {
        return reviewService.getReviewsByUser(userId).map { reviewConverter.convertToReviewDto(it) }
    }

    @GetMapping(params = ["upc"])
    fun getReviewsByProduct(@RequestParam("upc") upc: Int): List<ReviewDto> {
        return reviewService.getReviewsByProduct(upc).map { reviewConverter.convertToReviewDto(it) }
    }
}