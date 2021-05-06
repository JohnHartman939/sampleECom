package com.example.demo.repositories

import com.example.demo.entities.User
import org.springframework.data.repository.CrudRepository

interface UserRepo: CrudRepository<User,Int> {
    fun findByEmail(email: String?): User
}