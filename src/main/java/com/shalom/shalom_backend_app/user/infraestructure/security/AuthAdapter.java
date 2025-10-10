package com.shalom.shalom_backend_app.user.infraestructure.security;

import java.util.Date;

import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

import com.shalom.shalom_backend_app.user.domain.model.User;
import com.shalom.shalom_backend_app.user.domain.ports.out.AuthPort;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class AuthAdapter implements AuthPort {

    private static final String SECRET_KEY = "MiContraseñaMythos123x";
    private static final long EXPIRATION_MS = 1000 * 60 * 60;

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    @Override
    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("role", user.getRole().name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
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
