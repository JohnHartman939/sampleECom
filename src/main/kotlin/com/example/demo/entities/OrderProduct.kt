package com.example.demo.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable
import javax.persistence.*

@Embeddable
data class OrderProductKey(
    @Column(name = "orderId")
    var orderId: Int?,
    @Column(name = "productId")
    var upc: Int
    ): Serializable

@Entity
@Table(name = "OrderProduct")
data class OrderProduct (
    @EmbeddedId
    var orderId: OrderProductKey,

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "orderId")
    @JsonIgnoreProperties("orderProduct")
    var order: Order?,

    @ManyToOne
    @MapsId("upc")
    @JoinColumn(name = "upc")
    @JsonIgnoreProperties("orderProduct")
    var product: Product,

    var quantity: Int
        )
