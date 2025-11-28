package com.shalom.shalom_backend_app.tariff.infraestructure.web.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TariffRequestDTO {
    private Long id;

    @NotNull(message = "El routeId es requerido.")
    private Long routeId;

    @NotNull
    private Double basePrice;

    @NotNull
    private Double pricePerKg;
    private Double pricePerKm;
    private LocalDate effectiveDate;
}
