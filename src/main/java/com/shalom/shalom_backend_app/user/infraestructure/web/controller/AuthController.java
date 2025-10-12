package com.shalom.shalom_backend_app.user.infraestructure.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shalom.shalom_backend_app.shared.api.ApiResponse;
import com.shalom.shalom_backend_app.user.application.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // CUS00 - Iniciar sesion
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestParam String email, @RequestParam String password) {
        try {
            String token = authService.authenticate(email, password);
            return ResponseEntity.ok(ApiResponse.success("Inicio de sesion exitoso.", token));
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error interno del servidor."));
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<ApiResponse<String>> validate(@RequestParam String token) {
        try {
            String username = authService.validateToken(token);
            return ResponseEntity.ok(ApiResponse.success("Token valido.", username));
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error interno del servidor."));
        }
    }
}
