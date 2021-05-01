package com.example.demo.repositories

import com.example.demo.entities.Product
import org.springframework.data.repository.CrudRepository

interface ProductRepo: CrudRepository<Product,Int> {
}