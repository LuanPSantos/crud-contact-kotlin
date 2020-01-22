package com.luan.contact.user.repository

import com.luan.contact.user.model.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    @Query("SELECT user FROM User user WHERE user.username = :username")
    fun findByUsername(username: String): User
}