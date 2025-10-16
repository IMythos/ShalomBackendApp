package com.shalom.shalom_backend_app.user.infraestructure.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shalom.shalom_backend_app.shared.api.ApiResponse;
import com.shalom.shalom_backend_app.user.application.AuthService;
import com.shalom.shalom_backend_app.user.domain.model.User;
import com.shalom.shalom_backend_app.user.infraestructure.web.dto.request.LoginRequestDTO;
import com.shalom.shalom_backend_app.user.infraestructure.web.dto.response.LoginResponseDTO;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // CUS00 - Iniciar sesion
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(@RequestBody LoginRequestDTO request) {
        try {
            User user = authService.getUserByEmail(request.getEmail());
            String token = authService.authenticate(request.getEmail(), request.getPassword());
            LoginResponseDTO response = new LoginResponseDTO(token, user.getRole().name());
            return ResponseEntity.ok(ApiResponse.success("Inicio de sesion exitoso.", response));
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
