package com.example.demo.entities

import com.example.demo.datatranferobjects.ReviewDto
import javax.persistence.*

@Entity
data class Review (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val reviewId: Int = 0,
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
        reviewDto: ReviewDto,
        user: User,
        product: Product
    ): this(
        user = user,
        product = product,
        rating = reviewDto.rating,
        reviewText = reviewDto.reviewText,
        reviewTitle = reviewDto.reviewName
    )
}