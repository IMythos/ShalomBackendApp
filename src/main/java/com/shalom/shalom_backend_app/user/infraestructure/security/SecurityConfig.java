package com.shalom.shalom_backend_app.user.infraestructure.security;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
    
    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    
}
