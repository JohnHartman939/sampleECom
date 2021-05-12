package com.example.demo.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "Product")
data class Product (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var upc: Int,
    var sku: String?,
    var productName: String?,
    var productDescription: String?,
    var price: Float?,
    @OneToMany(mappedBy = "product")
    @JsonIgnoreProperties("product")
    var orderProduct: MutableList<OrderProduct>? = null

)