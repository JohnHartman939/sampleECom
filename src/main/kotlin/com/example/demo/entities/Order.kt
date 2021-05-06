package com.example.demo.entities

import java.io.Serializable
import javax.persistence.*

@Embeddable
data class OrderKey (
//    @Column(name = "orderId")
//    var orderIdNum: Int,
    @Column(name = "userId")
    var userId: Int,
    @Column(name = "productId")
    var upc: Int
    ): Serializable

@Entity
@Table(name = "UserProductOrder")
data class Order (
    @EmbeddedId
    var orderId: OrderKey,

//    @MapsId("orderIdNum")
//    var orderIdNum: Int,

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "id")
    var user: User,

    @ManyToOne
    @MapsId("upc")
    @JoinColumn(name = "upc")
    var product: Product,

    var rating: Int,
    var comment: String
        )
