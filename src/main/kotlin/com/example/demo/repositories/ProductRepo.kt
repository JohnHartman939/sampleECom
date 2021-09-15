package com.example.demo.repositories

import com.example.demo.entities.Product
import org.springframework.core.annotation.Order
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepo: CrudRepository<Product,Int> {
    fun findByPriceBetween(lower: Double, higher: Double): List<Product>
    fun findByProductNameOrProductDescriptionContaining(nameKeyword: String, descriptionKeyword: String): List<Product>
    fun findByUpcAndProductName(upc: Int, productName: String): Product
    fun findByUpc(upc: Int?): Product
}