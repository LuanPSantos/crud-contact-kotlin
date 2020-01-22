package com.luan.contact.user.service

import com.luan.contact.user.model.User
import com.luan.contact.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return this.userRepository.findByUsername(username)
    }

    fun save(user: User) {
        user.password = BCryptPasswordEncoder().encode(user.password)
        this.userRepository.save(user)
    }
}