package com.example.demo.datatranferobjects

import com.example.demo.entities.OrderProduct
import com.example.demo.entities.Product
import com.example.demo.entityinterfaces.IProduct
import com.fasterxml.jackson.annotation.JsonProperty

data class ProductDto(
    @JsonProperty("productCode")
    var upc:Int,
    @JsonProperty("name")
    var productName: String,
    @JsonProperty("description")
    var productDescription: String,
    @JsonProperty("productPrice")
    var price: Double,
        ): IProduct {


    constructor(product: Product): this(upc = product.upc, productName = product.productName, productDescription = product.productDescription, price = product.price){
    }
}


data class ProductDto2(
    var upc: Int,
    var productName: String,
    var productDescription: String,
    var orderProduct: testOP
){
    constructor(     upc: Int,
                     productName: String,
                    productDescription: String,
     orderProduct: OrderProduct): this(
        upc = upc, productName= productName, productDescription= productDescription, orderProduct= testOP(orderProduct.orderId.orderId)
    )
}

data class testOP(
    var orderId: Int
)