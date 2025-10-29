package com.shalom.shalom_backend_app.service.infraestructure.web.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ServiceRequestDTO {
    private Long id;

    @NotBlank(message = "Debe tener un nombre de servicio.")
    private String serviceName;

    @NotBlank(message = "Debe tener una descripcion.")
    private String description;

    @NotBlank(message = "Debe tener un precio base.")
    private Double baseCost;

    @Min(value = 1, message = "Los dias estimados del servicio deben ser, por lo menos, 1.")
    private Integer estimatedDays;
    private Boolean isActive;
}
