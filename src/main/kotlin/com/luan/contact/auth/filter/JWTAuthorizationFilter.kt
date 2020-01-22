package com.luan.contact.auth.filter

import com.luan.contact.auth.constant.AuthConstant
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest;

class JWTAuthorizationFilter : GenericFilterBean() {
    override fun doFilter(request: ServletRequest, respose: ServletResponse, filterChain: FilterChain) {
        val token: String = (request as HttpServletRequest).getHeader(AuthConstant.HEADER_STRING).orEmpty()

        if("" != token) {
            val username = Jwts.parser()
                .setSigningKey(AuthConstant.SECRET_KEY)
                .parseClaimsJws(token.replace(AuthConstant.TOKEN_PREFIX, ""))
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
}