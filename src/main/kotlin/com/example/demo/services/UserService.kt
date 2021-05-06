package com.example.demo.services

import com.example.demo.datatranferobjects.UserDto
import com.example.demo.entities.User
import com.example.demo.repositories.UserRepo
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService (val userRepo: UserRepo) {
    fun addUser(user: User){
        userRepo.save(user)
    }

    fun getUserById(id: Int): Optional<User> {
        return userRepo.findById(id)
    }

    fun getUserByEmail(email: String?): User {
        return userRepo.findByEmail(email)
    }

    fun checkForAccountWithEmail(email: String?): Boolean {
        return userRepo.findByEmail(email) != null
    }

    fun registerNewUserAccount(user: User): User {
        if(checkForAccountWithEmail(user.email)) {
            ///todo
        }
        return userRepo.save(user)
    }
}