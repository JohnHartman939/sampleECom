package com.example.demo.repositories

import com.example.demo.entities.Product
import org.springframework.data.repository.CrudRepository

interface ProductRepo: CrudRepository<Product,Int> {
    fun findByPriceBetween(lower: Float, higher: Float): List<Product>
    fun findByProductNameOrProductDescriptionContaining(nameKeyword: String, descriptionKeyword: String): List<Product>
}