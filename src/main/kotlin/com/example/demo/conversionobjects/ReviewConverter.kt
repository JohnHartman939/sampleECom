package com.example.demo.conversionobjects

import com.example.demo.datatranferobjects.ReviewDto
import com.example.demo.entities.Product
import com.example.demo.entities.Review
import com.example.demo.entities.User
import com.example.demo.errors.ProductNotFoundException
import com.example.demo.errors.UserNotFoundException
import com.example.demo.repositories.ProductRepo
import com.example.demo.repositories.UserRepo
import org.springframework.core.convert.converter.Converter
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Component

@Component
class ReviewToDtoConverter: Converter<Review, ReviewDto> {
    override fun convert(review: Review): ReviewDto {
        return ReviewDto(review)
    }
}

@Component
class DtoToReviewConverter(val userRepo: UserRepo, val productRepo: ProductRepo) : Converter<ReviewDto,Review> {

    override fun convert(reviewDto: ReviewDto): Review {

        return Review(
            reviewDto = reviewDto,
            user = try {userRepo.findByIdUser(reviewDto.user.idUser, User::class.java)}
            catch (e: EmptyResultDataAccessException)
            { throw UserNotFoundException("The user could not be found")},
            product = try {productRepo.findByUpc(reviewDto.product.upc, Product::class.java)} catch (e: EmptyResultDataAccessException) {throw ProductNotFoundException("The product could not be found")}
        )
    }
}

