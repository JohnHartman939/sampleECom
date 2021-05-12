package com.example.demo.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "Users")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    var email: String?,
    var password: String?,
    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    var orders: MutableList<Order>? = null

){
    constructor(userId: Int): this(id = userId, email = null, password = null){
        id = userId
    }
}