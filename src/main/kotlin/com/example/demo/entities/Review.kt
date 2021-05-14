package com.example.demo.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
data class Review (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val reviewId: Int?,
    @ManyToOne
    @JoinColumn(name = "id")
    @JsonIgnoreProperties("reviews")
    var user: User?,
    @ManyToOne
    @JoinColumn(name = "upc")
    @JsonIgnoreProperties("reviews")
    var product: Product?,
    val rating: Int,
    val reviewTitle: String,
    val reviewText: String
        )