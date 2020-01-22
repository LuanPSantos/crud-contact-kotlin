package com.luan.contact.auth

import com.fasterxml.jackson.databind.ObjectMapper
import com.luan.contact.user.model.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import java.util.*
import javax.crypto.SecretKey
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JWTAuthenticationFilter(
    url: String,
    authManager: AuthenticationManager) : AbstractAuthenticationProcessingFilter(AntPathRequestMatcher(url)) {

    init {
        authenticationManager = authManager
    }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {

        val user: User = ObjectMapper().readValue<User>(request.inputStream.readBytes(), User::class.java)

        return this.authenticationManager.authenticate(UsernamePasswordAuthenticationToken(user.username,user.password, user.authorities))
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {
        val jwt = Jwts.builder()
            .setSubject(authResult.name)
            .setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
            .compact()

        response.setHeader(HEADER_STRING, "$TOKEN_PREFIX $jwt")
    }

    companion object {
        const val EXPIRATION_TIME = 860_000_000
        val SECRET_KEY: SecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512)
        var TOKEN_PREFIX = "Bearer"
        var HEADER_STRING = "Authorization"
    }
}