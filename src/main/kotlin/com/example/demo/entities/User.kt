package com.example.demo.entities

import javax.persistence.*

@Entity
@Table(name = "Users")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    var email: String,
    var password: String,
    @OneToMany(mappedBy = "user")
    var products: MutableSet<Order>? = null

)