package com.example.demo.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http?.authorizeRequests()
            ?.antMatchers("/login", "/register")?.permitAll()
            ?.anyRequest()?.permitAll()
            ?.and()?.logout()?.permitAll()
            ?.and()?.httpBasic()
            ?.and()?.csrf()?.disable()

    }

    @Bean
    fun getPasswordEncoder(): BCryptPasswordEncoder{
        return BCryptPasswordEncoder()
    }
}