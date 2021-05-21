package com.example.demo.entities

import com.example.demo.datatranferobjects.ProductDto
import javax.persistence.*

@Entity
@Table(name = "Product")
data class Product (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var upc: Int?,
    var productName: String,
    var productDescription: String,
    var price: Double,
    @OneToMany(mappedBy = "product")
    var orderProduct: MutableList<OrderProduct> = mutableListOf(),
    @OneToMany(mappedBy = "product")
    val reviews: MutableList<Review> = mutableListOf()
) {
    constructor(productDto: ProductDto): this(
        upc = null,
        productName = productDto.name,
        productDescription = productDto.description,
        price = productDto.price)
}