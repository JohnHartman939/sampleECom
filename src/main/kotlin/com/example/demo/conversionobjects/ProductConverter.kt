package com.example.demo.conversionobjects

import com.example.demo.datatranferobjects.ProductDto
import com.example.demo.entities.Product
import org.springframework.stereotype.Component

@Component
class ProductConverter {
    fun convertToProduct(productDto: ProductDto): Product{
        return Product(productDto)
    }

    fun convertToProductDto(product: Product): ProductDto{
        return ProductDto(product)
    }
}