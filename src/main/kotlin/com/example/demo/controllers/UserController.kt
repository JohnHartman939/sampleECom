package com.example.demo.controllers

import com.example.demo.conversionobjects.UserConverter
import com.example.demo.datatranferobjects.UserDto
import com.example.demo.entities.User
import com.example.demo.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/user")
class UserController( val userService: UserService, val userConverter: UserConverter) {
    @PostMapping("/register")
    fun saveUser(@RequestBody userDto: UserDto) {
        userService.addUser(userConverter.convertToUser(userDto))
    }

    @GetMapping(params = ["email"])
    fun getUserByEmail(@RequestParam("email") email: String): UserDto {
        return userConverter.convertToUserDto(userService.getUserByEmail(email))
    }

    @GetMapping(params = ["id"])
    fun getUserById(@RequestParam("id") id: Int): UserDto {
        return userConverter.convertToUserDto(userService.getUserById(id))
    }
}