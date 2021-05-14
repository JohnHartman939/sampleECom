package com.example.demo.datatranferobjects

import com.example.demo.entities.Review

data class ReviewDto (
    var rating: Int,
    var reviewName: String,
    var reviewText: String
){
    constructor(review: Review): this(review.rating,review.reviewTitle,review.reviewText)
}