package com.example.demo.repositories

import com.example.demo.datatranferobjects.OrderDto
import com.example.demo.entities.Order
import com.example.demo.entities.User
import com.example.demo.entityinterfaces.IOrder
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface OrderRepo: CrudRepository<Order,Int> {

    fun <T:IOrder>findByUser_IdUser(user: Int, type: Class<T>): List<T>

//    @Query( value = "select new com.example.demo.datatranferobjects.OrderDTO(o.orderId, o.firstName, o.lastName, o.address, o.city, o.state, o.zip, o.orderSum, p.upc, p.productName) from Order AS o \n" +
//            "Join OrderProduct as op on op.orderId = o.orderId \n" +
//            "join Product as p on op.upc = p.upc\n" +
//            "join User as u on o.id = u.idUser where u.idUser = :idUser")
//    fun custom(@Param("idUser") user: Int) : List<OrderDto>

    //fun customFindByUser(user: User): OrderDto


   // fun <T:IOrder>findByUser_IdUser(idUser: Int, type: Class<T>): List<Order>

}