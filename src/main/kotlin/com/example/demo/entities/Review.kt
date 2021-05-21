package com.example.demo.entities

import com.example.demo.datatranferobjects.ReviewDtoRequest
import javax.persistence.*

@Entity
data class Review (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val reviewId: Int?,
    @ManyToOne
    @JoinColumn(name = "id")
    var user: User,
    @ManyToOne
    @JoinColumn(name = "upc")
    var product: Product,
    var rating: Int,
    var reviewTitle: String,
    var reviewText: String
    ) {
    constructor(
        reviewDto: ReviewDtoRequest,
        user: User,
        product: Product
    ): this(
        reviewId = null,
        user = user,
        product = product,
        rating = reviewDto.rating,
        reviewText = reviewDto.reviewText,
        reviewTitle = reviewDto.reviewName
    )
}