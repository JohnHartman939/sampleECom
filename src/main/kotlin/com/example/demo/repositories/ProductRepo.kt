package com.example.demo.repositories

import com.example.demo.datatranferobjects.AdminData
import com.example.demo.datatranferobjects.ProductDto
import com.example.demo.entities.Product
import com.example.demo.entityinterfaces.IProduct
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepo: CrudRepository<Product,Int> {
    fun findByPriceBetween(lower: Double, higher: Double): List<ProductDto>
    fun findByProductNameOrProductDescriptionContaining(nameKeyword: String, descriptionKeyword: String): List<ProductDto>
    fun <T: IProduct> findByUpcAndProductName(upc: Int, productName: String, type: Class<T>): T
    fun <T: IProduct> findByUpc(upc: Int?, type: Class<T> ): T

    @Query(value = "SELECT avg(price) FROM Product")
    fun averagePrice(): Double

    @Query(value = "SELECT max(price) FROM Product")
    fun maxPrice(): Double

    @Query(value = "SELECT new com.example.demo.datatranferobjects.AdminData(avg(price) AS averageProductPrice, max(price))FROM Product")
    fun averageAndMaxPrice(): AdminData
}