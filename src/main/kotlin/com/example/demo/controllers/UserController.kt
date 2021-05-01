package com.example.demo.controllers

import com.example.demo.entities.User
import com.example.demo.services.UserService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/user")
class UserController( val userService: UserService) {
    @PostMapping
    fun saveUser(@RequestBody user: User) {
        userService.addUser(user)
    }

//    @GetMapping
//    fun getUserByEmail(@RequestParam("email") email: String): User {
//        return userService.getUserByEmail(email)
//    }

    @GetMapping
    fun getUserById(@RequestParam("id") id: Int): Optional<User> {
        return userService.getUserById(id)
    }
}