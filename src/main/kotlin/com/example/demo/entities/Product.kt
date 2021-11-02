package com.example.demo.entities

import com.example.demo.datatranferobjects.ProductDto
import com.example.demo.entityinterfaces.IProduct
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
):IProduct {
    constructor(productDto: ProductDto): this(
        upc = productDto.upc,
        productName = productDto.productName,
        productDescription = productDto.productDescription,
        price = productDto.price)
}