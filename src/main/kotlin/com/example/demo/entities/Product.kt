package com.example.demo.entities

import javax.persistence.*

@Entity
@Table(name = "Product")
data class Product (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var upc: Int,
    var productName: String,
    var productDescription: String,
    var price: Float,
    @OneToMany(mappedBy = "product")
    var users: MutableSet<Order>? = null
        )