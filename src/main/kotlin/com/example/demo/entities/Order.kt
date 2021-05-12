package com.example.demo.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "Orders")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var orderId: Int?,
    var firstName: String,
    var lastName: String,
    var address: String,
    var city: String,
    var state: String,
    var zip: String,
    @ManyToOne
    @JoinColumn(name = "id")
    @JsonIgnoreProperties("orders")
    var user: User,
    @JsonIgnoreProperties("order")
    @OneToMany(mappedBy = "order", cascade = arrayOf(CascadeType.PERSIST))
    var orderProduct: MutableList<OrderProduct>? = null
        )
