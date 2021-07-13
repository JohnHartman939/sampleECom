package com.example.demo.conversionobjects

import com.example.demo.datatranferobjects.UserDto
import com.example.demo.entities.User
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class UserToDtoConverter: Converter<User,UserDto> {
    override fun convert(user: User): UserDto {
        return UserDto(user)
    }
}

@Component
class DtoToUserConverter: Converter<UserDto,User> {
    override fun convert(userDto: UserDto): User{
        return User(userDto)
    }
}