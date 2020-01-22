package com.luan.contact.configuration

import com.luan.contact.auth.JWTAuthorizationFilter
import com.luan.contact.auth.JWTAuthenticationFilter
import com.luan.contact.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfiguration @Autowired constructor(
    private val userService: UserService
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .cors().and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/users").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(JWTAuthenticationFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userService).passwordEncoder(BCryptPasswordEncoder())
    }
}