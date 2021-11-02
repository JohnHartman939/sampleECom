package com.example.demo.datatranferobjects

import com.example.demo.entities.Product
import com.example.demo.entities.Review
import com.example.demo.entities.User
import com.example.demo.entityinterfaces.IReview

data class ReviewDto(
    var rating: Int,
    var reviewName: String,
    var reviewText: String,
    var user: IUserID,
    var product: ReviewUpc
): IReview {

    constructor(review: Review): this(
        rating = review.rating,
        reviewName = review.reviewName,
        reviewText = review.reviewText,
        user = IUserID(review.user.idUser),
        product = ReviewUpc(review.product.upc)
    )

    constructor(rating: Int, reviewName: String,reviewText: String, user: User, product: Product): this(
        rating = rating,
        reviewName = reviewName,
        reviewText = reviewText,
        user = IUserID(user.idUser),
        product = ReviewUpc(product.upc)
    )
}

class IUserID (
    val idUser: Int
        )

class ReviewUpc (
    val upc: Int
        )