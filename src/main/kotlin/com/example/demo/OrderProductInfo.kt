package com.example.demo

interface OrderProductInfo {
    val quantity: Int?
    val order: OrderInfo?
    val product: ProductInfo?

    interface OrderInfo {
        val orderId: Int?
    }

    interface ProductInfo {
        val upc: Int?
        val price: Double?
        val productDescription: String?
    }
}