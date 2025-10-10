package com.shalom.shalom_backend_app.user.domain.ports.in;

// CUS00: Iniciar sesion
public interface AuthUseCase {
    String authenticate(String email, String password);
    String validateToken(String token);
}
