package com.example.demo.conversionobjects

import com.example.demo.datatranferobjects.OrderDtoRequest
import com.example.demo.datatranferobjects.OrderDtoResponse
import com.example.demo.entities.*
import com.example.demo.repositories.ProductRepo
import com.example.demo.repositories.UserRepo
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class OrderToDtoConverter: Converter<Order,OrderDtoResponse> {

    override fun convert(order: Order): OrderDtoResponse {
        //return OrderDtoResponse(order)
        return OrderDtoResponse(order.orderId, order.lastName, order.orderSum)
    }
}

@Component
class DtoToOrderConverter(val userRepo: UserRepo, val productRepo: ProductRepo) : Converter<OrderDtoRequest,Order> {

    override fun convert(orderDtoRequest: OrderDtoRequest): Order {
        var order = Order(orderDtoRequest,
            user = userRepo.findByIdUser(orderDtoRequest.userId, User::class.java),
            orderedProducts = orderDtoRequest.orderInfo.products.map { OrderProduct(
                orderId = OrderProductKey(upc = it.upc), product = productRepo.findByUpcAndProductName(it.upc, it.productName, Product::class.java) , quantity = it.quantity, order = Order()) })
        order.orderProduct.forEach { it.order = order }
        return order

    }
}