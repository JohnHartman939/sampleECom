package com.example.demo.datatranferobjects

data class UserDto (
    var firstName: String,
    var lastName: String,
    var password: String,
    var matchingPassword: String,
    var email: String
)