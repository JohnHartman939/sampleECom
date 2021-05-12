package com.example.demo.datatranferobjects

import com.example.demo.entities.Order
import com.example.demo.entities.OrderProduct

data class OrderDto(
    var orderID: Int?,
    var orderInfo: OrderInfo
        ){
    constructor(order: Order) : this(order.orderId, OrderInfo(order)) {
        orderID=order.orderId
        orderInfo=OrderInfo(order)
    }
}

data class OrderInfo(
    var deliveryName: String,
    var deliveryAddress: String,
    var deliveryCity: String,
    var deliveryState: String,
    var deliveryZip: String,
    var products: List<OrderProductDto>?
){
    constructor(order: Order) : this(order.firstName + " " + order.lastName, order.address, order.city, order.state, order.zip, order.orderProduct?.map { OrderProductDto(it) } as MutableList<OrderProductDto>?) {
        deliveryName = order.firstName + " " + order.lastName
        deliveryAddress = order.address
        deliveryCity = order.city
        deliveryState = order.state
        deliveryZip = order.zip
        products = order.orderProduct?.map { OrderProductDto(it) } as MutableList<OrderProductDto>?
    }
}

data class OrderProductDto(
    var sku: String?,
    var productName: String?,
    var quantity: Int
){
    constructor( orderProduct: OrderProduct) : this(orderProduct.product.sku, orderProduct.product.productName, orderProduct.quantity) {
        sku = orderProduct.product.sku
        productName = orderProduct.product.productName
        quantity = orderProduct.quantity
    }
}