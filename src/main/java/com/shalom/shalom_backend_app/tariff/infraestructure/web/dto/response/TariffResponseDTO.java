package com.shalom.shalom_backend_app.tariff.infraestructure.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TariffResponseDTO {
    private Long id;
    private Long routeId;
    private String routeOrigin;
    private String routeDestination;
    private Double basePrice;
    private Double pricePerKg;
    private Double pricePerKm;
    private String effectiveDate;
}
