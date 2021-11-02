package com.example.demo.controllers

import com.example.demo.datatranferobjects.ProductDto
import com.example.demo.entities.Product
import com.example.demo.errors.CustomConversionException
import com.example.demo.errors.PutBadRequest
import com.example.demo.services.ProductService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.core.convert.ConversionService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductController( val productService: ProductService, @Qualifier("mvcConversionService")val conversionService: ConversionService)  {
    @PostMapping
    fun addProduct(@RequestBody productDto: ProductDto): ProductDto{
        return conversionService.convert(productService.addProduct(conversionService.convert(productDto, Product::class.java)?: throw CustomConversionException("There was a problem")), ProductDto::class.java)?: throw CustomConversionException("There was a problem")
    }

    @PutMapping(params = ["upc"])
    fun updateProduct(@RequestParam("upc") upc: Int, @RequestBody productDto: ProductDto): ProductDto {
        return if (upc == productDto.upc) conversionService.convert(productService.updateProduct(conversionService.convert(productDto, Product::class.java)?: throw CustomConversionException("There was a problem")), ProductDto::class.java) ?: throw CustomConversionException("There was a problem") else throw PutBadRequest("upc in url not equal to request body")
    }

    @GetMapping()
    fun getAllProducts(): List<ProductDto> {
        return productService.getAllProducts().map { conversionService.convert(it, ProductDto::class.java) ?: throw CustomConversionException("There was a problem") }
    }

    @GetMapping(params = ["upc"])
    fun getProductByUpc(@RequestParam("upc") upc: Int): ProductDto {
        return productService.getProductByUpc(upc)
    }

    @GetMapping(params = ["low", "high"])
    fun getProductsBetweenPrice(@RequestParam("low", required = true) low: Double, @RequestParam( "high", required = true) high: Double): List<ProductDto> {
        return productService.getProductsByPriceBetween(low,high)
    }

    @GetMapping(params = ["keyword"])
    fun searchForProductsByNameOrDescription(@RequestParam("keyword", required = true) keyword: String): List<ProductDto> {
        return productService.searchForProducts(keyword)
    }

    @GetMapping( params = ["upc", "name"])
    fun getProductByUpcAndName(@RequestParam("upc") upc: Int, @RequestParam("name")name: String): ProductDto{
        return productService.findProductByUpcAndName(upc, name)
    }
}