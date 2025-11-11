package com.shalom.shalom_backend_app.shipment.infraestructure.web.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageRequestDTO {
    private Long id;

    @NotNull(message = "Debe tener un peso.")
    @Positive(message = "El peso debe ser un número mayor que 0.")
    private Double weight;

    @NotNull(message = "Debe tener una altura.")
    @Positive(message = "La altura debe ser un número mayor que 0.")
    private Double height;

    @NotNull(message = "Debe tener una longitud.")
    @Positive(message = "La longitud debe ser un número mayor que 0.")
    private Double length;
    private String description;
}
