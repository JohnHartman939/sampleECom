package com.example.demo.controllers

import com.example.demo.datatranferobjects.ReviewDto
import com.example.demo.entities.Review
import com.example.demo.errors.CustomConversionException
import com.example.demo.services.ReviewService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.core.convert.ConversionService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/review")
class ReviewController(val reviewService: ReviewService, @Qualifier("mvcConversionService")val conversionService: ConversionService) {

    @PutMapping(params = ["upc", "userId"])
    fun updateReview(@RequestParam("upc") upc: Int, @RequestParam("userId") userId: Int, @RequestBody reviewDto: ReviewDto): ReviewDto {
        reviewDto.userId = userId
        reviewDto.upc = upc
        return conversionService.convert(reviewService.updateReview(upc, userId, conversionService.convert(reviewDto, Review::class.java) ?: throw CustomConversionException("There was a problem")), ReviewDto::class.java) ?: throw CustomConversionException("There was a problem")

    }

    @PostMapping(params = ["upc", "userId"])
    fun saveReview(@RequestParam("upc") upc: Int, @RequestParam("userId") userId: Int, @RequestBody reviewDto: ReviewDto): ReviewDto {
        reviewDto.userId = userId
        reviewDto.upc = upc
        return conversionService.convert(reviewService.saveReview(
            upc = upc,
            userId = userId,
            review = conversionService.convert(reviewDto, Review::class.java) ?: throw CustomConversionException("There was a problem")
        ), ReviewDto::class.java) ?: throw CustomConversionException("There was a problem")
    }

    @GetMapping(params = ["userId"])
    fun getReviewsByUser(@RequestParam("userId") userId: Int): List<ReviewDto> {
        return reviewService.getReviewsByUser(userId).map { conversionService.convert(it, ReviewDto::class.java) ?: throw CustomConversionException("There was a problem") }
    }

    @GetMapping(params = ["upc"])
    fun getReviewsByProduct(@RequestParam("upc") upc: Int): List<ReviewDto> {
        return reviewService.getReviewsByProduct(upc).map { conversionService.convert(it, ReviewDto::class.java) ?: throw CustomConversionException("There was a problem") }
    }
}