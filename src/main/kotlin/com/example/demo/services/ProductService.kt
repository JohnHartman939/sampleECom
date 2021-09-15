package com.example.demo.services

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

    fun getProductByUpc(upc: Int): Product {
        return productRepo.findByUpc(upc)
    }

    fun getProductsByPriceBetween(low: Double, high: Double): List<Product> {
        return productRepo.findByPriceBetween(low, high)
    }

    fun searchForProducts(keyword: String): List<Product> {
        return productRepo.findByProductNameOrProductDescriptionContaining(keyword, keyword)
        }

    fun findProductByUpcAndName(upc: Int, name: String): Product{
        return productRepo.findByUpcAndProductName(upc, name)
    }

}