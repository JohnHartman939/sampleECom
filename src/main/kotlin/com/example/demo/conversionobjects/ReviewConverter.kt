package com.example.demo.conversionobjects

import com.example.demo.datatranferobjects.ReviewDtoRequest
import com.example.demo.datatranferobjects.ReviewDtoResponse
import com.example.demo.entities.Review
import com.example.demo.repositories.ProductRepo
import com.example.demo.repositories.UserRepo
import org.springframework.stereotype.Component

@Component
class ReviewConverter(val userRepo: UserRepo, val productRepo: ProductRepo) {

    fun convertToReviewDtoResponse(review: Review): ReviewDtoResponse {
        return ReviewDtoResponse(review)
    }

    fun convertToReview(reviewDtoRequest: ReviewDtoRequest): Review{
        return Review(reviewDtoRequest, userRepo.findByIdUser(reviewDtoRequest.userId), productRepo.findByUpc(reviewDtoRequest.upc) )
    }
}