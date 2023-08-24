package com.portfolio.ohousev1.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtUtil {
    private Key key;

    public JwtUtil(String secret){
        this.key = Keys.hmacShaKeyFor(secret.getBytes());

    }
    public String createToken(Long id, String email){
        String token = Jwts.builder()
                .claim("userid",id)
                .claim("email", email)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return token;
    }
}
