package com.example.demo.controllers

import com.example.demo.datatranferobjects.ReviewDto
import com.example.demo.entities.Review
import com.example.demo.errors.AlreadyReviewedException
import com.example.demo.errors.NotOrderedException
import com.example.demo.services.ReviewService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/review")
class ReviewController(val reviewService: ReviewService) {

    @PutMapping(params = ["upc", "userId"])
    fun updateReview(@RequestParam("upc") upc: Int, @RequestParam("userId") userId: Int, @RequestBody review: Review): ReviewDto {
        return reviewService.updateReview(upc, userId, review)
    }

    @PostMapping(params = ["upc", "userId"])
    fun saveReview(@RequestParam("upc") upc: Int, @RequestParam("userId") userId: Int, @RequestBody review: Review): ReviewDto {
        return reviewService.saveReview(upc, userId, review)
    }

    @GetMapping(params = ["userId"])
    fun getReviewsByUser(@RequestParam("userId") userId: Int): List<ReviewDto> {
        return reviewService.getReviewsByUser(userId)
    }

    @GetMapping(params = ["upc"])
    fun getReviewsByProduct(@RequestParam("upc") upc: Int): List<ReviewDto> {
        return reviewService.getReviewsByProduct(upc)
    }
}