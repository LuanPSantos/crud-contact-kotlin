package com.luan.contact.user.controller

import com.luan.contact.user.model.User
import com.luan.contact.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController @Autowired constructor(
    private val userService: UserService
) {

    @PostMapping("users")
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody user: User) {
        this.userService.create(user)
    }
}