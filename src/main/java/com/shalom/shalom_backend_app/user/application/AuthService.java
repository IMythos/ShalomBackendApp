package com.shalom.shalom_backend_app.user.application;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shalom.shalom_backend_app.user.domain.model.User;
import com.shalom.shalom_backend_app.user.domain.ports.in.AuthUseCase;
import com.shalom.shalom_backend_app.user.domain.ports.out.AuthPort;
import com.shalom.shalom_backend_app.user.domain.ports.out.UserRepositoryPort;

@Service
public class AuthService implements AuthUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final AuthPort authPort;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepositoryPort userRepositoryPort, AuthPort authPort, PasswordEncoder passwordEncoder) {
        this.userRepositoryPort = userRepositoryPort;
        this.authPort = authPort;
        this.passwordEncoder = passwordEncoder;
    }

    // CUS00: Iniciar sesion
    @Override
    public String authenticate(String email, String password) {
        Optional<User> userOpt = userRepositoryPort.findByEmail(email);

        if (userOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado.");
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new RuntimeException("Credenciales invalidas.");
        }

        return authPort.generateToken(user);
    }

    public String validateToken(String email) {
        if (!authPort.validateToken(email)) {
            throw new RuntimeException("Token invalido o expirado.");
        }

        return authPort.extractUsername(email);
    }

    public User getUserByEmail(String email) {
        return userRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
    }

    public User getUserByUsername(String username) {
        return userRepositoryPort.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
    }
}
