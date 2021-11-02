package com.example.demo.datatranferobjects

import com.example.demo.entities.Order
import com.example.demo.entities.OrderProduct
import com.example.demo.entityinterfaces.IOrder
import org.springframework.data.rest.core.config.Projection


data class OrderDtoRequest(
    var userId: Int,
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

 class OrderDtoResponse /*(
    var orderId: Int,
    var firstName: String,
    var lastName: String,
    var address: String,
    var city: String,
    var state: String,
    var zip: String,
    var orderProduct: List<ProductUpc>,
    var orderSum: Double
)*/:IOrder{
     var orderId = 0
    // var firstName = ""
     var lastName = ""
    // var address = ""
   //  var city = ""
   //  var state = ""
   //  var zip = ""
   //  var orderProduct = listOf<ProductUpc>()
     var orderSum = 0.0
    constructor(orderId: Int,
                firstName: String,
                lastName: String,
                address: String,
                city: String,
                state: String,
                zip: String,
                orderProduct: List<ProductUpc>,
                orderSum: Double) {
        this.orderId = orderId
       // this.firstName = firstName
        this.lastName = lastName
      //  this.address = address
      //  this.city = city
       // this.state = state
      //  this.zip = zip
      //  this.orderProduct = orderProduct
        this.orderSum = orderSum
    }
     constructor(/*order: Order*/orderId: Int, lastName: String, orderSum: Double){
         this.orderId = orderId
        // this.firstName = order.firstName
         this.lastName = lastName
        // this.address = order.address
        // this.city = order.city
       //  this.state = order.state
//         this.orderProduct = order.orderProduct.map { it -> ProductUpc(it.product.upc, it.product.productName) }
         this.orderSum = orderSum
     }
}


//class ProductUpc (
//    //   val upc: Int,
//    val upc: Int,
//    val productName: String
//)
//    constructor(order: Order): this (
//        orderId = order.orderId,
//        firstName = order.firstName,
//        lastName =order.lastName,
//        address = order.address,
//        city = order.city,
//        state = order.state,
//        zip = order.zip,
//        orderSum = order.orderSum,
//        orderProduct = order.orderProduct.map { it -> ProductUpc(OrderedProductDescription(it.product.productName, it.product.upc)) }
//    )
//}
//interface ProductUpc {
//    val product: OrderedProductDescription
//}
//
//interface OrderedProductDescription {
//    var productName: String
//    var upc: Int
//}



//data class OrderDto(
//    var firstName: String,
//    var lastName: String,
//    var address: String,
//    var orderProduct: List<ProductUpc>,
//    var orderSum: Double
//): IOrder
//{
//    constructor(firstName: String, lastName: String, address: String, orderProduct: List<OrderProduct>, orderSum: Double): this(
//        firstName = firstName,
//        lastName = lastName,
//        address = address,
//        orderProduct = orderProduct.map { it -> ProductUpc(it.product.upc, it.product.productName) },
//        orderSum = orderSum
//    )
//}

//interface OrderDto: IOrder {
//    var firstName: String
//    var lastName: String
//    var address: String
//    var city: String
//    var state: String
//    var zip: String
//    var orderProduct: List<ProductUpc>
//    var orderSum: Double
//}

data class OrderDto(
    var orderId: Int,
    var firstName: String,
    var lastName: String,
    var address: String,
    var city: String,
    var state: String,
    var zip: String,
    var orderProduct: List<OrderedProduct>,
    var orderSum: Double
): IOrder

data class ProductUpc(
    var product: OrderedProduct
)

data class OrderedProduct(
    var upc: Int,
    var productName: String
)