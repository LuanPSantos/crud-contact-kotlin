package com.luan.contact.user.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    private var username: String,
    private var password: String,
    var role: String = "USER"
): UserDetails {

    fun setPassword(password: String) {
        this.password = password
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority("ROLE_$role"))
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String {
        return this.username
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return this.password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

}