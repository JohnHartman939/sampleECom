package com.example.demo.services

import com.example.demo.datatranferobjects.UserDto
import com.example.demo.entities.User
import com.example.demo.repositories.UserRepo
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService (val userRepo: UserRepo) {
    fun addUser(user: User): User{
        return userRepo.save(user)
    }

    fun getUserById(id: Int): UserDto {
        return userRepo.findByIdUser(id, UserDto::class.java)
    }

    fun getUserByEmail(email: String): UserDto {
        return userRepo.findByEmail(email)
    }

    fun checkForAccountWithEmail(email: String): Boolean {
        return userRepo.findByEmail(email) != null
    }

    fun updateUser(user: User): User {
        return userRepo.save(user)
    }
}