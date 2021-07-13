package com.example.demo.conversionobjects

import com.example.demo.datatranferobjects.OrderDtoRequest
import com.example.demo.datatranferobjects.OrderDtoResponse
import com.example.demo.entities.Order
import com.example.demo.entities.OrderProduct
import com.example.demo.entities.OrderProductKey
import com.example.demo.errors.UserNotProvidedException
import com.example.demo.repositories.ProductRepo
import com.example.demo.repositories.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.DependsOn
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

//this converter does work
@Component
class OrderToDtoConverter: Converter<Order,OrderDtoResponse> {

    override fun convert(order: Order): OrderDtoResponse {
        return OrderDtoResponse(order)
    }
}

//this converter does not work
@Component
class DtoToOrderConverter(val userRepo: UserRepo, val productRepo: ProductRepo) : Converter<OrderDtoRequest,Order> {

    override fun convert(orderDtoRequest: OrderDtoRequest): Order {
        var order = Order(orderDtoRequest,
            user = userRepo.findByIdUser(orderDtoRequest.userId ?: throw UserNotProvidedException("The User was not provided")),
            orderedProducts = orderDtoRequest.orderInfo.products.map { OrderProduct(
                orderId = OrderProductKey(upc = it.upc), product = productRepo.findByUpcAndProductName(it.upc, it.productName), quantity = it.quantity ) })
        order.orderProduct.forEach { it.order = order }
        return order

    }
}