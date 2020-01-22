package com.luan.contact.auth

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import javax.crypto.SecretKey
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest;

class JWTAuthorizationFilter : GenericFilterBean() {
    override fun doFilter(request: ServletRequest, respose: ServletResponse, filterChain: FilterChain) {
        val token: String = (request as HttpServletRequest).getHeader(HEADER_STRING).orEmpty()

        if("" != token) {
            val username = Jwts.parser()
                .setSigningKey(JWTAuthenticationFilter.SECRET_KEY)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .body
                .subject

            if(username != null) {
                val authentication =
                    UsernamePasswordAuthenticationToken(username, null, mutableListOf())

                SecurityContextHolder.getContext().authentication = authentication
            }
        }

        filterChain.doFilter(request, respose)
    }

    private companion object {
        var TOKEN_PREFIX = "Bearer"
        var HEADER_STRING = "Authorization"
    }
}