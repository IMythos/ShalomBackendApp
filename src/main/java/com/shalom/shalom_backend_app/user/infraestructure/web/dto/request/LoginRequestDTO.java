package com.shalom.shalom_backend_app.user.infraestructure.web.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

    @Email(message = "El correo electronico no es valido.")
    @NotBlank(message = "El correo electronico es obligatorio.")
    private String email;

    @NotBlank(message = "La contraseñeña es obligatoria.")
    private String password;
}
