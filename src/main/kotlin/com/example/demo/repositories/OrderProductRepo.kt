package com.example.demo.repositories

import com.example.demo.datatranferobjects.OrderDto
import com.example.demo.datatranferobjects.OrderProductDto2
import com.example.demo.entities.OrderProduct
import com.example.demo.entities.OrderProductKey
import org.springframework.data.repository.CrudRepository

interface OrderProductRepo: CrudRepository<OrderProduct,OrderProductKey> {


    fun findByOrder_User_IdUser(idUser: Int): List<OrderProductDto2>

}