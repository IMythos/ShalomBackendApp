package com.shalom.shalom_backend_app.user.infraestructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "clients")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity extends UserEntity {

    @NotNull(message = "El dni es obligatorio.")
    @Column(unique = true ,length = 8)
    private String dni;
    private String address;

    @Column(length = 9)
    private String phone;
}
