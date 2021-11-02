package com.example.demo.entities

import com.example.demo.datatranferobjects.ReviewDto
import com.example.demo.entityinterfaces.IReview
import javax.persistence.*

@Entity
data class Review (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val reviewId: Int = 0,
    @ManyToOne
    @JoinColumn(name = "id")
    var user: User,
    @ManyToOne
    @JoinColumn(name = "upc")
    var product: Product,
    var rating: Int,
    var reviewName: String,
    var reviewText: String
    ):IReview {
    constructor(
        reviewDto: ReviewDto,
        user: User,
        product: Product
    ): this(
        user = user,
        product = product,
        rating = reviewDto.rating,
        reviewText = reviewDto.reviewText,
        reviewName = reviewDto.reviewName
    )
}