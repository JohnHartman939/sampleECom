package com.example.demo.services

import com.example.demo.datatranferobjects.ProductDto
import com.example.demo.entities.Product
import com.example.demo.repositories.ProductRepo
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductService(val productRepo: ProductRepo)  {
    fun addProduct(product: Product): Product {
        return productRepo.save(product)
    }

    fun updateProduct(product: Product): Product {
        return productRepo.save(product)
    }

    fun getAllProducts(): List<ProductDto> {
        return productRepo.findAll().map { product -> convertProductToDto(product) }
    }

    fun getProductByUpc(upc: Int): Optional<Product> {
        return productRepo.findById(upc)
    }

    fun getProductsByPriceBetween(low: Float, high: Float): List<ProductDto> {
        return productRepo.findByPriceBetween(low, high).map { product -> convertProductToDto(product) }
    }

    fun searchForProducts(keyword: String): List<ProductDto> {
        return productRepo.findByProductNameOrProductDescriptionContaining(keyword, keyword).map { product -> convertProductToDto(product) }
        }

    fun findProductBySkuAndName(sku: String?, name: String?): Product{
        return productRepo.findBySkuAndProductName(sku!!, name!!)
    }

    private fun convertProductToDto(product: Product): ProductDto{
        return ProductDto(
            upc = product.upc,
            name = product.productName,
            description = product.productDescription,
            price = product.price)
    }
}