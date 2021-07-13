package com.example.demo.datatranferobjects

import com.example.demo.entities.Review

data class ReviewDto (
    var rating: Int,
    var reviewName: String,
    var reviewText: String,
    var userId: Int? = null,
    var upc: Int? = null
){

    constructor(review: Review): this(
        rating = review.rating,
        reviewName = review.reviewTitle,
        reviewText = review.reviewText,
    )
}