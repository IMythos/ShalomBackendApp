package com.shalom.shalom_backend_app.user.infraestructure.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shalom.shalom_backend_app.user.application.AuthService;
import com.shalom.shalom_backend_app.user.application.ManageUserService;
import com.shalom.shalom_backend_app.user.domain.model.User;
import com.shalom.shalom_backend_app.user.infraestructure.web.dto.request.LoginRequestDTO;
import com.shalom.shalom_backend_app.user.infraestructure.web.dto.response.LoginResponseDTO;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final ManageUserService userService;

    public AuthController(AuthService authService, ManageUserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    // CUS00 - Iniciar sesion
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        String token = authService.authenticate(request.getEmail(), request.getPassword());

        User user = userService.findUserByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        LoginResponseDTO response = new LoginResponseDTO(token, user.getUsername(), user.getRole().name());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validate(@RequestParam String token) {
        String username = authService.validateToken(token);
        return ResponseEntity.ok("Token valido. Usuario: " + username);
    }
}
