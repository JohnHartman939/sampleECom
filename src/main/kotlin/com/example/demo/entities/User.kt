package com.example.demo.entities

import com.example.demo.datatranferobjects.UserDto
import com.example.demo.entityinterfaces.IUser
import javax.persistence.*

@Entity
@Table(name = "Users")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var idUser: Int = 0 ,
    var email: String,
    var password: String,
    @OneToMany(mappedBy = "user")
    var orders: MutableList<Order> = mutableListOf(),
    @OneToMany(mappedBy = "user")
    val reviews: MutableList<Review> = mutableListOf()
): IUser {
    constructor(userDto: UserDto): this( email = userDto.email, password = userDto.password)
}