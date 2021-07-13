package com.example.demo

import com.example.demo.conversionobjects.*
import com.example.demo.repositories.ProductRepo
import com.example.demo.repositories.UserRepo
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.core.convert.support.DefaultConversionService

import org.springframework.core.convert.ConversionService
import org.springframework.format.FormatterRegistry


@SpringBootApplication
@EnableConfigurationProperties
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}



