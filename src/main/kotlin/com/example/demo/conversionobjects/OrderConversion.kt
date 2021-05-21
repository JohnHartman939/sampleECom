package com.example.demo.conversionobjects
//
//import com.example.demo.datatranferobjects.OrderDto
//import com.example.demo.datatranferobjects.OrderInfo
//import com.example.demo.datatranferobjects.OrderProductDto
//import com.example.demo.entities.Order
//import com.example.demo.entities.OrderProduct
//import com.example.demo.entities.OrderProductKey
//import com.example.demo.repositories.ProductRepo
//import com.example.demo.repositories.UserRepo
//import org.springframework.stereotype.Component
//
//class OrderConversion(
//    var orderId: Int?,
//    var firstName: String,
//    var lastName: String,
//    var address: String,
//    var city: String,
//    var state: String,
//    var zip: String,
//    var userConversion: UserConversion,
//    var orderProductConversion: List<OrderProductConversion>,
//    var orderSum: Double?
//){
//    constructor(orderDto: OrderDto, userId: Int): this(orderId = null, firstName = orderDto.orderInfo.deliveryFirstName,
//        lastName = orderDto.orderInfo.deliveryLastName,
//        address = orderDto.orderInfo.deliveryAddress,
//        city = orderDto.orderInfo.deliveryCity,
//        state = orderDto.orderInfo.deliveryState,
//        zip = orderDto.orderInfo.deliveryZip,
//        userConversion = UserConversion(userId = userId),
//        orderProductConversion = orderDto.orderInfo.products.map
//        { OrderProductConversion(
//            qunatity = it.quantity,
//            productConversion = ProductConversion(
//                upc = it.upc,
//                productName = it.productName
//            )
//        ) }, orderSum = orderDto.total
//    )
//
//    constructor(order: Order): this(orderId = order.orderId,
//        firstName = order.firstName,
//        lastName = order.lastName,
//        address = order.address,
//        city = order.city,
//        state = order.state,
//        zip = order.zip,
//        userConversion = UserConversion(order.user.idUser),
//        orderProductConversion = order.orderProduct.map {
//            OrderProductConversion(
//                qunatity = it.quantity,
//                productConversion = ProductConversion(
//                    upc = it.product.upc,
//                    productName = it.product.productName
//                )
//            ) }, orderSum = order.orderSum
//    )
//}
//
//class UserConversion(
//    var userId: Int?
//)
//
//class OrderProductConversion(
//    var qunatity: Int,
//    var productConversion: ProductConversion
//)
//
//class ProductConversion(
//    var upc: Int?,
//    var productName: String
//)
//
////@Component
////class OrderConverterold(val userRepo: UserRepo, val productRepo: ProductRepo){
////    fun convertToOrder(orderConversion: OrderConversion): Order {
////
////        val order = Order(
////            orderId = null,
////            firstName = orderConversion.firstName,
////            lastName = orderConversion.lastName,
////            address = orderConversion.address,
////            city = orderConversion.city,
////            state = orderConversion.state,
////            zip = orderConversion.zip,
////            user = userRepo.findById(orderConversion.userConversion.userId).get(),
////            orderProduct = mutableListOf(),
////            orderSum = orderConversion.orderSum
////        )
////
////        order.orderProduct = orderConversion.orderProductConversion.map {
////            val product = productRepo.findByUpcAndProductName(
////                upc = it.productConversion.upc,
////                productName = it.productConversion.productName
////            )
////            OrderProduct(
////                orderId = OrderProductKey(orderId = order.orderId, upc = product.upc),
////                order = order,
////                product = product,
////                quantity = it.qunatity
////            ) }.toMutableList()
////
////        return order
////    }
////
////    fun convertToOrderDto( orderConversion: OrderConversion): OrderDto{
////        return OrderDto(
////            orderID = orderConversion.orderId, total = orderConversion.orderSum,
////            orderInfo = OrderInfo(deliveryFirstName = orderConversion.firstName,
////                deliveryLastName = orderConversion.lastName,
////                deliveryAddress = orderConversion.address,
////                deliveryCity = orderConversion.city,
////                deliveryState = orderConversion.state,
////                deliveryZip = orderConversion.zip,
////                products = orderConversion.orderProductConversion.map {
////                    OrderProductDto(
////                        upc = it.productConversion.upc,
////                        productName = it.productConversion.productName,
////                        quantity = it.qunatity
////                    ) })
////        )
////    }
////}