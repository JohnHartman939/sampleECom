package com.example.demo.entities

import java.io.Serializable
import javax.persistence.*

@Embeddable
data class OrderProductKey(
    @Column(name = "orderId")
    var orderId: Int = 0,
    @Column(name = "productId")
    var upc: Int
    ): Serializable

@Entity
@Table(name = "OrderProduct")
data class OrderProduct (
    @EmbeddedId
    var orderId: OrderProductKey,

    @ManyToOne
    @MapsId("upc")
    @JoinColumn(name = "upc")
    var product: Product,

    var quantity: Int
        ){
    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "orderId")
    lateinit var order: Order
}
