package com.example.demo.testData

import com.example.demo.datatranferobjects.UserDto

class Data {
    companion object{
        fun getUserDto(): UserDto {
            return UserDto(email = "test@gmail.com", password = "test123")
        }
    }

}