package com.example.demo.controllers

import com.example.demo.entities.Product
import com.example.demo.services.ProductService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/products")
class ProductController( val productService: ProductService)  {
    @PostMapping
    fun addProduct(@RequestBody product: Product) {
        productService.addProduct(product)
    }

    @GetMapping
    fun getProductByUpc(@RequestParam("upc") upc: Int): Optional<Product> {
        return productService.getProductByUpc(upc)
    }
}