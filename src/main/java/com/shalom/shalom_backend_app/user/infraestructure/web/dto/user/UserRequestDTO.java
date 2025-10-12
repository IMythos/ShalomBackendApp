package com.shalom.shalom_backend_app.user.infraestructure.web.dto.user;

import java.time.LocalDate;

import com.shalom.shalom_backend_app.user.domain.model.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class UserRequestDTO {
    private Long id;

    @NotBlank(message = "El nombre de usuario es obligatorio.")
    private String username;

    @Email(message = "El correo electronico no es valido.")
    @NotBlank(message = "El correo electronico es obligatorio.")
    private String email;

    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres.")
    private String password;

    private Role role;
    private String typeUser;

    private String dni;
    private String address;
    private String phone;

    private String position;
    private LocalDate hireDate;
}
