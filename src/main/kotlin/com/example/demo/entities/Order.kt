package com.example.demo.entities

import com.example.demo.datatranferobjects.OrderDtoRequest
import com.example.demo.entityinterfaces.IOrder
import org.hibernate.annotations.CreationTimestamp
import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name = "Orders")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var orderId: Int = 0,
    var firstName: String,
    var lastName: String,
    var address: String,
    var city: String,
    var state: String,
    var zip: String,
    @ManyToOne
    @JoinColumn(name = "id")
    var user: User,
    @OneToMany(mappedBy = "order", cascade = [CascadeType.PERSIST])
    var orderProduct: MutableList<OrderProduct> = mutableListOf(),
    var orderSum: Double
    ):IOrder {
    constructor(orderDtoRequest: OrderDtoRequest, user: User, orderedProducts: List<OrderProduct>): this(
    firstName = orderDtoRequest.orderInfo.deliveryFirstName,
    lastName = orderDtoRequest.orderInfo.deliveryLastName,
    address = orderDtoRequest.orderInfo.deliveryAddress,
    city = orderDtoRequest.orderInfo.deliveryCity,
    state = orderDtoRequest.orderInfo.deliveryState,
    zip = orderDtoRequest.orderInfo.deliveryZip,
    user = user,
    orderProduct = orderedProducts.toMutableList(),
    orderSum = orderedProducts.sumOf { it.product.price * it.quantity }
    )

    constructor(

    ) : this(
        firstName = "",
        lastName = "",
        address = "",
        city = "",
        state = "",
        zip = "",
        user = User(0,"","", mutableListOf(), mutableListOf()),
        orderProduct = mutableListOf(),
        orderSum = 0.0
    )

    @field:CreationTimestamp
    lateinit var orderDate: Timestamp
}
