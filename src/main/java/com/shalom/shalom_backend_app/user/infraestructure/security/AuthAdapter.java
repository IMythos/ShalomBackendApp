package com.shalom.shalom_backend_app.user.infraestructure.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.shalom.shalom_backend_app.user.domain.model.User;
import com.shalom.shalom_backend_app.user.domain.ports.out.AuthPort;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class AuthAdapter implements AuthPort {

    private final SecretKey key;

    private final long expiration;

    public AuthAdapter(@Value("${app.jwt.key}") String key, @Value("${app.jwt.expiration}") long expiration) {
        this.key = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
        this.expiration = expiration;
    }

    @Override
    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("role", user.getRole().name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String extractUsername(String token) {
        Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
        return claims.getSubject();
    }
    
}
