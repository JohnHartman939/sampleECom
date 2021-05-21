package com.example.demo.datatranferobjects

import com.example.demo.entities.Review

data class ReviewDtoRequest (
    var rating: Int,
    var reviewName: String,
    var reviewText: String,
    var userId: Int,
    var upc: Int
)

data class ReviewDtoResponse (
    var rating: Int,
    var reviewName: String,
    var reviewText: String,
    var upc: Int?
        ){
    constructor(review: Review): this(
        rating = review.rating,
        reviewName = review.reviewTitle,
        reviewText = review.reviewText,
        upc = review.product.upc
    )
}