package com.example.demo.datatranferobjects

import com.example.demo.entities.User
import com.example.demo.entityinterfaces.IUser

data class UserDto (
    var email: String,
    var password: String,
): IUser {
    constructor(user: User): this(
        email = user.email,
        password = user.password,
    )
}