package com.example.chatapp.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

public class JwtUtil {
    public String generateToken(String username) {
        String secret = System.getenv("CHAT_JWT_SECRET");
        if (secret == null || secret.isBlank()) {
            throw new IllegalStateException("Set CHAT_JWT_SECRET before generating tokens");
        }
        Instant now = Instant.now();
        return Jwts.builder()
            .subject(username)
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plus(10, ChronoUnit.HOURS)))
            .signWith(Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret)), Jwts.SIG.HS256)
            .compact();
    }
}
