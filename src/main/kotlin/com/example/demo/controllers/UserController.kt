package com.example.demo.controllers

import com.example.demo.datatranferobjects.UserDto
import com.example.demo.entities.User
import com.example.demo.errors.CustomConversionException
import com.example.demo.services.UserService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.core.convert.ConversionService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(val userService: UserService, @Qualifier("mvcConversionService")val conversionService: ConversionService) {
    @PostMapping("/register")
    fun saveUser(@RequestBody userDto: UserDto): UserDto {
        return conversionService.convert(userService.addUser(conversionService.convert(userDto, User::class.java) ?: throw CustomConversionException("There was a problem")),UserDto::class.java) ?: throw CustomConversionException("There was a problem")
    }

    @GetMapping(params = ["email"])
    fun getUserByEmail(@RequestParam("email") email: String): UserDto {
        return userService.getUserByEmail(email)
    }

    @GetMapping(params = ["id"])
    fun getUserById(@RequestParam("id") id: Int): UserDto {
        return userService.getUserById(id)

    }

}