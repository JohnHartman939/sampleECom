package com.example.demo.conversionobjects

import com.example.demo.datatranferobjects.OrderDtoRequest
import com.example.demo.datatranferobjects.OrderDtoResponse
import com.example.demo.entities.Order
import com.example.demo.entities.OrderProduct
import com.example.demo.entities.OrderProductKey
import com.example.demo.repositories.ProductRepo
import com.example.demo.repositories.UserRepo
import org.springframework.stereotype.Component

@Component
class OrderConverter( val userRepo: UserRepo, val productRepo: ProductRepo) {
    fun convertToOrder(orderDtoRequest: OrderDtoRequest, userId: Int): Order {
        var order = Order(orderDtoRequest, user = userRepo.findByIdUser(userId), orderedProducts = orderDtoRequest.orderInfo.products.map { OrderProduct(orderId = OrderProductKey(
            upc = it.upc), product = productRepo.findByUpcAndProductName(it.upc, it.productName), quantity = it.quantity ) })
        order.orderProduct.forEach { it.order = order }
        return order
    }

    fun convertToOrderResponseDto(order: Order): OrderDtoResponse{
        return OrderDtoResponse(order)
    }
}