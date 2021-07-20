package com.example.demo.serviceTests

import com.example.demo.entities.*
import com.example.demo.errors.AlreadyReviewedException
import com.example.demo.errors.NotOrderedException
import com.example.demo.repositories.OrderRepo
import com.example.demo.repositories.ProductRepo
import com.example.demo.repositories.ReviewRepo
import com.example.demo.repositories.UserRepo
import com.example.demo.services.ReviewService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.*

class reviewServiceTests() {

    @Test
    fun whenUserDidNotOrderProduct_thenThrowNotOrderedException() {
        val user = User(idUser = 0, email = "llama", password = "duck")
        val product1 = Product(upc = 0, productName = "anvil", productDescription = "lots of metal", price = 9000.1)
        val product2 = Product(upc = 1, productName = "anvil", productDescription = "lots of metal", price = 9000.1)
        val orders = mutableListOf(
            Order(
                orderId = 0,
                firstName = "johnny",
                lastName = "appleseed",
                address = "123 fake st",
                city = "springfield",
                state = "MA",
                zip = "12345",
                user = user,
                orderProduct = mutableListOf(
                    OrderProduct(
                        OrderProductKey(orderId = 0, upc = 0), product = product1, quantity = 2
                    )
                ),
                orderSum = 10.0
            )
        )
        val mockOrderRepo: OrderRepo = mock()
        val mockUserRepo: UserRepo = mock()
        val mockProductRepo: ProductRepo = mock()
        val mockReviewRepo: ReviewRepo = mock()
        whenever(mockUserRepo.findByIdUser(0)).thenReturn(user)
        whenever(mockOrderRepo.findAllByUser(user)).thenReturn(orders)

        val reviewService = ReviewService(mockReviewRepo, mockUserRepo, mockProductRepo, mockOrderRepo)

        Assertions.assertThrows(NotOrderedException::class.java) {
            reviewService.saveReview(
                5, 0, Review(
                    reviewId = 0,
                    user = user,
                    product = product2,
                    rating = 5,
                    reviewTitle = "Amazing",
                    reviewText = "it was so good"
                )
            )
        }
    }

    @Test
    fun whenUserAlreadyReviewedProduct_thenThrowAlreadyReviewedException() {
        val user = User(idUser = 0, email = "llama", password = "duck")
        val product1 = Product(upc = 0, productName = "anvil", productDescription = "lots of metal", price = 9000.1)
        val product2 = Product(upc = 1, productName = "anvil", productDescription = "lots of metal", price = 9000.1)
        val orders = mutableListOf(
            Order(
                orderId = 0,
                firstName = "johnny",
                lastName = "appleseed",
                address = "123 fake st",
                city = "springfield",
                state = "MA",
                zip = "12345",
                user = user,
                orderProduct = mutableListOf(
                    OrderProduct(
                        OrderProductKey(orderId = 0, upc = 0), product = product1, quantity = 2
                    )
                ),
                orderSum = 10.0
            )
        )
        orders[0].orderProduct[0].product.reviews.add(Review(
            reviewId = 0,
            user = user,
            product = product1,
            rating = 5,
            reviewTitle = "Amazing",
            reviewText = "it was so good"
        ))
        val mockOrderRepo: OrderRepo = mock()
        val mockUserRepo: UserRepo = mock()
        val mockProductRepo: ProductRepo = mock()
        val mockReviewRepo: ReviewRepo = mock()
        whenever(mockUserRepo.findByIdUser(0)).thenReturn(user)
        whenever(mockOrderRepo.findAllByUser(user)).thenReturn(orders)

        val reviewService = ReviewService(mockReviewRepo, mockUserRepo, mockProductRepo, mockOrderRepo)

        Assertions.assertThrows(AlreadyReviewedException::class.java) {
            reviewService.saveReview(
                0, 0, Review(
                    reviewId = 0,
                    user = user,
                    product = product2,
                    rating = 5,
                    reviewTitle = "Amazing",
                    reviewText = "it was so good"
                )
            )
        }
    }

    @Test
    fun whenUserHasNotReviewedAndDidOrderProduct_thenSaveReview() {
        val user = User(idUser = 0, email = "llama", password = "duck")
        val product1 = Product(upc = 0, productName = "anvil", productDescription = "lots of metal", price = 9000.1)
        val product2 = Product(upc = 1, productName = "anvil", productDescription = "lots of metal", price = 9000.1)
        val orders = mutableListOf(
            Order(
                orderId = 0,
                firstName = "johnny",
                lastName = "appleseed",
                address = "123 fake st",
                city = "springfield",
                state = "MA",
                zip = "12345",
                user = user,
                orderProduct = mutableListOf(
                    OrderProduct(
                        OrderProductKey(orderId = 0, upc = 0), product = product1, quantity = 2
                    )
                ),
                orderSum = 10.0
            )
        )
        val review = Review(
            reviewId = 0,
            user = user,
            product = product1,
            rating = 5,
            reviewTitle = "Amazing",
            reviewText = "it was so good"
        )
        val mockOrderRepo: OrderRepo = mock()
        val mockUserRepo: UserRepo = mock()
        val mockProductRepo: ProductRepo = mock()
        val mockReviewRepo: ReviewRepo = mock()
        whenever(mockUserRepo.findByIdUser(0)).thenReturn(user)
        whenever(mockOrderRepo.findAllByUser(user)).thenReturn(orders)
        whenever(mockProductRepo.findById(0)).thenReturn(Optional.of(product1))
        whenever(mockUserRepo.findById(0)).thenReturn(Optional.of(user))
        whenever(mockReviewRepo.save(review)).thenReturn(review)

        val reviewService = ReviewService(mockReviewRepo, mockUserRepo, mockProductRepo, mockOrderRepo)

        Assertions.assertEquals(
            reviewService.saveReview(
                0, 0, review
            ),review)

    }
}