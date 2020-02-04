package com.luan.contact.user.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.luan.contact.user.model.User
import com.luan.contact.user.service.UserService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest (@Autowired val mockMvc: MockMvc ) {

    @MockkBean
    private lateinit var userService: UserService;

    @Test
    fun `Should create user`() {
        every { userService.create(any()) } returns User(1, "username", "password")

        mockMvc.perform(post("/users")
            .content(ObjectMapper().writeValueAsString(User(null, "username", "password")))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated)
    }
}