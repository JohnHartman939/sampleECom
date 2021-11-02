package com.example.demo.services

import com.example.demo.datatranferobjects.ProductDto
import com.example.demo.entities.Product
import com.example.demo.repositories.ProductRepo
import org.springframework.stereotype.Service

@Service
class ProductService(val productRepo: ProductRepo)  {
    fun addProduct(product: Product): Product {
        return productRepo.save(product)
    }

    fun updateProduct(product: Product): Product {
        return productRepo.save(product)
    }

    fun getAllProducts(): MutableIterable<Product> {
        return productRepo.findAll()
    }

    fun getProductByUpc(upc: Int): ProductDto {
        return productRepo.findByUpc(upc, ProductDto::class.java)
    }

    fun getProductsByPriceBetween(low: Double, high: Double): List<ProductDto> {
        return productRepo.findByPriceBetween(low, high)
    }

    fun searchForProducts(keyword: String): List<ProductDto> {
        return productRepo.findByProductNameOrProductDescriptionContaining(keyword, keyword)
        }

    fun findProductByUpcAndName(upc: Int, name: String): ProductDto{
        return productRepo.findByUpcAndProductName(upc, name, ProductDto::class.java)
    }

}