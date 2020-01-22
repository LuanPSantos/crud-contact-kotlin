package com.luan.contact.auth.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.luan.contact.auth.constant.AuthConstant
import com.luan.contact.user.model.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import java.util.*
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

        return this.authenticationManager
            .authenticate(UsernamePasswordAuthenticationToken(user.username,user.password, user.authorities))
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {
        val jwt = Jwts.builder()
            .setSubject(authResult.name)
            .setExpiration(Date(System.currentTimeMillis() + AuthConstant.EXPIRATION_TIME))
            .signWith(AuthConstant.SECRET_KEY, SignatureAlgorithm.HS512)
            .compact()

        response.setHeader(AuthConstant.HEADER_STRING, AuthConstant.TOKEN_PREFIX + jwt)
    }
}