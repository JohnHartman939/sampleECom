package com.example.demo.conversionobjects

import com.example.demo.datatranferobjects.OrderDto
import com.example.demo.datatranferobjects.OrderInfo
import com.example.demo.datatranferobjects.OrderProductDto
import com.example.demo.entities.Order
import com.example.demo.entities.OrderProduct
import com.example.demo.entities.OrderProductKey
import com.example.demo.repositories.ProductRepo
import com.example.demo.repositories.UserRepo
import org.springframework.stereotype.Component

class OrderConversion(
    var orderId: Int?,
    var firstName: String,
    var lastName: String,
    var address: String,
    var city: String,
    var state: String,
    var zip: String,
    var userConversion: UserConversion?,
    var orderProductConversion: List<OrderProductConversion>?,
    var orderSum: Double?
){
    constructor(orderDto: OrderDto, userId: Int): this(null, orderDto.orderInfo.deliveryFirstName,
        orderDto.orderInfo.deliveryLastName,
        orderDto.orderInfo.deliveryAddress,
        orderDto.orderInfo.deliveryCity,
        orderDto.orderInfo.deliveryState,
        orderDto.orderInfo.deliveryZip,
        UserConversion(userId),
        orderDto.orderInfo.products?.map
            { OrderProductConversion(it.quantity,
                ProductConversion(it.sku,
                    it.productName)) }, orderDto.total)

    constructor(order: Order): this( order.orderId,
        order.firstName,
        order.lastName,
        order.address,
        order.city,
        order.state,
        order.zip,
        null,
        order.orderProduct?.map {
            OrderProductConversion(it.quantity,
                ProductConversion(it.product.sku,
                    it.product.productName)) }, order.orderSum)
}

class UserConversion(
    var userId: Int
)

class OrderProductConversion(
    var qunatity: Int,
    var productConversion: ProductConversion
)

class ProductConversion(
    var sku: String,
    var productName: String
)

@Component
class OrderConverter(val userRepo: UserRepo, val productRepo: ProductRepo){
    fun convertToOrder(orderConversion: OrderConversion): Order {

        var products = orderConversion.orderProductConversion?.map {
            productRepo.findBySkuAndProductName(it.productConversion.sku, it.productConversion.productName)
        }


        var order = Order(null,
            orderConversion.firstName,
            orderConversion.lastName,
            orderConversion.address,
            orderConversion.city,
            orderConversion.state,
            orderConversion.zip,
            userRepo.findById(orderConversion.userConversion?.userId!!).get(), null, orderConversion.orderSum)

        order.orderProduct = orderConversion.orderProductConversion?.map {
            var product = productRepo.findBySkuAndProductName(it.productConversion.sku, it.productConversion.productName)
            OrderProduct(OrderProductKey(order.orderId,product.upc),order, product, it.qunatity) }?.toMutableList()

        return order
    }

    fun convertToOrderDto( orderConversion: OrderConversion): OrderDto{
        return OrderDto(orderConversion.orderId, orderConversion.orderSum,
                            OrderInfo(orderConversion.firstName,
                                orderConversion.lastName,
                                orderConversion.address,
                                orderConversion.city,
                                orderConversion.state,
                                orderConversion.zip,
                                orderConversion.orderProductConversion?.map {
                                    OrderProductDto( it.productConversion.sku,
                                        it.productConversion.productName,
                                        it.qunatity) }))
    }
}