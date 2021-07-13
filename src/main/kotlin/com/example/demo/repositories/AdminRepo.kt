package com.example.demo.repositories

//import com.example.demo.entities.AdminData
//import org.springframework.data.jpa.repository.Query
//import org.springframework.data.repository.CrudRepository
//
//interface AdminRepo: CrudRepository<AdminData, Int> {
//    @Query("SELECT date_part(intervalOne, orderDate::date) as intervalOne, date_part(intervalTwo, orderDate::date) as intervalTwo, AVG(orderSum), MAX(orderSum) FROM orders GROUP BY intervalTwo, intervalOne")
//    fun findMaxAndAverageOrderValueByTimeInterval(intervalOne: String, intervalTwo: String): AdminData
//}