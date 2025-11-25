package com.shalom.shalom_backend_app.user.infraestructure.web.dto.user;

import java.time.LocalDate;

import com.shalom.shalom_backend_app.user.domain.model.Role;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String fullname;
    private String email;
    private Role role;

    private String dni;
    private String address;
    private String phone;
    
    private String position;
    private LocalDate hireDate;
}
