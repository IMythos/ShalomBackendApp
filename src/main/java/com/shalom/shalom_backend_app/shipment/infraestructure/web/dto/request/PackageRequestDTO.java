package com.shalom.shalom_backend_app.shipment.infraestructure.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageRequestDTO {
    private Long id;

    @NotBlank(message = "Debe tener un peso.")
    private Double weight;

    @NotBlank(message = "Debe tener una altura.")
    private Double height;

    @NotBlank(message = "Debe tener una longitud.")
    private Double length;
    private String description;
}
