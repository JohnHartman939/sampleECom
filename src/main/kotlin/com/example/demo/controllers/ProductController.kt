package com.example.demo.controllers

import com.example.demo.datatranferobjects.ProductDto
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

    @GetMapping()
    fun getAllProducts(): List<ProductDto> {
        return productService.getAllProducts()
    }

    @GetMapping(params = ["upc"])
    fun getProductByUpc(@RequestParam("upc") upc: Int): Optional<Product> {
        return productService.getProductByUpc(upc)
    }

    @GetMapping(params = ["low", "high"])
    fun getProductsBetweenPrice(@RequestParam("low", required = true) low: Float, @RequestParam( "high", required = true) high: Float): List<ProductDto> {
        return productService.getProductsByPriceBetween(low,high)
    }

    @GetMapping(params = ["keyword"])
    fun searchForProductsByNameOrDescription(@RequestParam("keyword", required = true) keyword: String): List<ProductDto> {
        return productService.searchForProducts(keyword)
    }
}