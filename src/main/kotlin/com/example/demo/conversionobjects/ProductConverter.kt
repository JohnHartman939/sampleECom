package com.example.demo.conversionobjects

import com.example.demo.datatranferobjects.ProductDto
import com.example.demo.entities.Product
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class DtoToProductConverter: Converter<ProductDto, Product> {
    override fun convert(productDto: ProductDto): Product? {
        return Product(productDto)
    }
}
@Component
class ProductToDtoConverter: Converter<Product, ProductDto> {
    override fun convert(product: Product): ProductDto? {
        return ProductDto(product)
    }
}