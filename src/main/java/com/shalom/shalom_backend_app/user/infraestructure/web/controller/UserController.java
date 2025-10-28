package com.shalom.shalom_backend_app.user.infraestructure.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shalom.shalom_backend_app.shared.api.ApiResponse;
import com.shalom.shalom_backend_app.user.application.ManageUserService;
import com.shalom.shalom_backend_app.user.domain.model.Role;
import com.shalom.shalom_backend_app.user.domain.model.User;
import com.shalom.shalom_backend_app.user.infraestructure.mapper.UserMapper;
import com.shalom.shalom_backend_app.user.infraestructure.web.dto.user.UserRequestDTO;
import com.shalom.shalom_backend_app.user.infraestructure.web.dto.user.UserResponseDTO;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final ManageUserService userService;    

    public UserController(ManageUserService userService) {
        this.userService = userService;
    }

    // CUS01: Gestionar usuarios
    // # CUS01.1: Registrar usuarios
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDTO>> createUser(@RequestBody UserRequestDTO dto) {
        try {
            User domain = UserMapper.toDomain(dto);
            User created = userService.createUser(domain);

            return ResponseEntity.ok(ApiResponse.success("Usuario registrado correctamente.", UserMapper.toResponseDTO(created)));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error interno del servidor."));
        }
    }

    // # CUS01.2: Listar usuarios
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponseDTO>>> listUsers() {
        try {
            List<UserResponseDTO> users = userService.listUsers()
                    .stream()
                    .map(UserMapper::toResponseDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(ApiResponse.success("Usuarios listados correctamente.", users));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error al listar usuarios."));
        }
    }

    // # CUS01.3: Eliminar usuario
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@RequestParam Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok(ApiResponse.success("Usuario eliminado correctamente.", null));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error al eliminar usuario."));
        }
    }

    // #CUS01.4: Actualizar usuario 
    // - Bug: FK in email and dni columns (no fix)
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> updateUser(@RequestParam Long id, @RequestBody UserRequestDTO dto) {
        try {
            User domain = UserMapper.toDomain(dto);
            User updated = userService.updateUser(id, domain);

            return ResponseEntity.ok(ApiResponse.success("Usuario actualizado correctamente.", UserMapper.toResponseDTO(updated)));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error al actualizar usuario."));
        }
    }

    // # CUS01.5: Asignar rol a usuario
    public ResponseEntity<ApiResponse<UserResponseDTO>> assignRole(@PathVariable Long id, @RequestParam Role role) {
        try {
            userService.assignRole(id, role);
            User updated = userService.listUsers().stream()
                    .filter(u -> u.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
            return ResponseEntity.ok(ApiResponse.success("Rol asignado correctamente.", UserMapper.toResponseDTO(updated)));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error al asignar rol en usuario."));
        }
    }
}
