package com.example.demo.controllers

import com.example.demo.conversionobjects.ProductConverter
import com.example.demo.datatranferobjects.ProductDto
import com.example.demo.entities.Product
import com.example.demo.errors.PutBadRequest
import com.example.demo.services.ProductService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/products")
class ProductController( val productService: ProductService, val productConverter: ProductConverter)  {
    @PostMapping
    fun addProduct(@RequestBody productDto: ProductDto): ProductDto{
        return productConverter.convertToProductDto(productService.addProduct(productConverter.convertToProduct(productDto)))
    }

    @PutMapping(params = ["upc"])
    fun updateProduct(@RequestParam("upc") upc: Int, @RequestBody productDto: ProductDto): ProductDto {
        return if (upc == productDto.upc) productConverter.convertToProductDto(productService.updateProduct(productConverter.convertToProduct(productDto))) else throw PutBadRequest("upc in url not equal to request body")
    }

    @GetMapping()
    fun getAllProducts(): List<ProductDto> {
        return productService.getAllProducts().map { productConverter.convertToProductDto(it) }
    }

    @GetMapping(params = ["upc"])
    fun getProductByUpc(@RequestParam("upc") upc: Int): ProductDto {
        return productConverter.convertToProductDto(productService.getProductByUpc(upc))
    }

    @GetMapping(params = ["low", "high"])
    fun getProductsBetweenPrice(@RequestParam("low", required = true) low: Float, @RequestParam( "high", required = true) high: Float): List<ProductDto> {
        return productService.getProductsByPriceBetween(low,high).map { productConverter.convertToProductDto(it) }
    }

    @GetMapping(params = ["keyword"])
    fun searchForProductsByNameOrDescription(@RequestParam("keyword", required = true) keyword: String): List<ProductDto> {
        return productService.searchForProducts(keyword).map { productConverter.convertToProductDto(it) }
    }

    @GetMapping( params = ["upc", "name"])
    fun getProductByUpcAndName(@RequestParam("upc") upc: Int, @RequestParam("name")name: String): ProductDto{
        return productConverter.convertToProductDto(productService.findProductByUpcAndName(upc, name))
    }
}