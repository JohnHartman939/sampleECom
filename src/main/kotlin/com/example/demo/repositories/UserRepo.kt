package com.example.demo.repositories

import com.example.demo.datatranferobjects.UserDto
import com.example.demo.entities.User
import com.example.demo.entityinterfaces.IUser
import org.springframework.core.annotation.Order
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepo: CrudRepository<User,Int> {
    fun findByEmail(email: String): UserDto
    fun <T: IUser>findByIdUser(idUser: Int, type: Class<T>): T
}