package com.example.demo.datatranferobjects

import com.example.demo.entities.User

data class UserDto (
    var email: String,
    var password: String,
) {
    constructor(user: User): this(
        email = user.email,
        password = user.password,
    )
}