package com.example.demo.conversionobjects

import com.example.demo.datatranferobjects.UserDto
import com.example.demo.entities.User
import org.springframework.stereotype.Component

@Component
class UserConverter {
    fun convertToUserDto(user: User): UserDto{
        return UserDto(user)
    }

    fun convertToUser(userDto: UserDto): User{
        return User(userDto)
    }
}