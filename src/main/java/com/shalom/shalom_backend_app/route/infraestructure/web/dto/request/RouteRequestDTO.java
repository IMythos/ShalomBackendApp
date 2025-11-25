package com.shalom.shalom_backend_app.route.infraestructure.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RouteRequestDTO {
    private Long id;

    @NotBlank(message = "Debe tener un origen especifico.")
    private Long originId;

    @NotBlank(message = "Debe tener un destino especifico.")
    private Long destinationId;

    @NotBlank(message = "Debe contar con una distancia real.")
    private Double distanceKm;

    private String estimatedTime;
}
