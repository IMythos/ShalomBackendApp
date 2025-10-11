package com.shalom.shalom_backend_app.user.infraestructure.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shalom.shalom_backend_app.user.application.ManageUserService;
import com.shalom.shalom_backend_app.user.domain.model.User;
import com.shalom.shalom_backend_app.user.infraestructure.mapper.UserMapper;
import com.shalom.shalom_backend_app.user.infraestructure.web.dto.user.UserDTO;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final ManageUserService userService;    

    public UserController(ManageUserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO dto) {
        User domain = UserMapper.toDomain(dto);
        User created = userService.createUser(domain);

        return ResponseEntity.ok(UserMapper.toDTO(created));
    }
}
