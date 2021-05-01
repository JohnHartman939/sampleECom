package com.example.demo.services

import com.example.demo.entities.Product
import com.example.demo.repositories.ProductRepo
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductService( val productRepo: ProductRepo)  {
    fun addProduct(product: Product) {
        productRepo.save(product)
    }

    fun getProductByUpc(upc: Int): Optional<Product> {
        return productRepo.findById(upc)
    }
}