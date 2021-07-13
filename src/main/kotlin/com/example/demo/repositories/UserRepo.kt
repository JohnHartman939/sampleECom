package com.example.demo.repositories

import com.example.demo.entities.User
import org.springframework.core.annotation.Order
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepo: CrudRepository<User,Int> {
    fun findByEmail(email: String): User
    fun findByIdUser(idUser: Int): User
}