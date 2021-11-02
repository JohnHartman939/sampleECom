package com.example.demo.services

import com.example.demo.datatranferobjects.AdminData
import com.example.demo.repositories.ProductRepo
import org.springframework.stereotype.Service

@Service
class AdminService( val productRepo: ProductRepo) {
    fun getAdminData(intervalOne: String, intervalTwo: String): AdminData {
        return productRepo.averageAndMaxPrice()
    }
}