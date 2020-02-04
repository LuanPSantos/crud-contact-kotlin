package com.luan.contact.user.service

import com.luan.contact.user.model.User
import com.luan.contact.user.repository.UserRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootTest
class UserServiceTest {

    @MockkBean
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var userService: UserService

    @Test
    fun `Should create user`() {

        every { userRepository.save(any<User>()) } returns
                User(1, "username", BCryptPasswordEncoder().encode("password"))

        val user = userService.save(User(null, "username", "password"))

        Assertions.assertTrue(BCryptPasswordEncoder().matches("password", user.password))
    }
}