package com.example.demo.datatranferobjects

import com.example.demo.entities.Product

data class ProductDto(
    var upc: Int?,
    var name: String,
    var description: String,
    var price: Double
        ) {
    constructor(product: Product): this(upc = product.upc, name = product.productName, description = product.productDescription, price = product.price)
}