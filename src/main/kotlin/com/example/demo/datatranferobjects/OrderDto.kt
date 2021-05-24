package com.example.demo.datatranferobjects

import com.example.demo.entities.Order


data class OrderDtoRequest(
    var orderInfo: OrderInfo
        )


data class OrderInfo(
    var deliveryFirstName: String,
    var deliveryLastName: String,
    var deliveryAddress: String,
    var deliveryCity: String,
    var deliveryState: String,
    var deliveryZip: String,
    var products: List<OrderProductDto>
)

data class OrderProductDto(
    var upc: Int,
    var productName: String,
    var quantity: Int
)

data class OrderDtoResponse (
    val orderId: Int,
    val total: Double,
    val orderInfo: OrderInfo
) {
    constructor(order: Order): this(
        orderId = order.orderId,
        total = order.orderSum,
        orderInfo = OrderInfo(
            deliveryFirstName = order.firstName,
            deliveryLastName = order.lastName,
            deliveryAddress = order.address,
            deliveryCity = order.city,
            deliveryState = order.state,
            deliveryZip = order.state,
            products = order.orderProduct.map {
                OrderProductDto(
                    upc = it.product.upc,
                    productName = it.product.productName,
                    quantity = it.quantity
                )
            })
    )
}