package com.example.demo.entities

import com.example.demo.datatranferobjects.ProductDto
import javax.persistence.*

@Entity
@Table(name = "Product")
data class Product (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var upc: Int,
    var productName: String,
    var productDescription: String,
    var price: Double,
    @OneToMany(mappedBy = "product")
    var orderProduct: MutableList<OrderProduct> = mutableListOf(),
    @OneToMany(mappedBy = "product")
    val reviews: MutableList<Review> = mutableListOf()
) {
    constructor(productDto: ProductDto): this(
        upc = productDto.upc,
        productName = productDto.name,
        productDescription = productDto.description,
        price = productDto.price)
}