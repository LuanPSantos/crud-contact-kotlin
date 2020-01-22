package com.luan.contact.auth.constant

import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import javax.crypto.SecretKey

class AuthConstant {
    companion object {
        const val EXPIRATION_TIME = 860_000_000
        val SECRET_KEY: SecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512)
        var TOKEN_PREFIX = "Bearer "
        var HEADER_STRING = "Authorization"
    }
}