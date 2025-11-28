package com.shalom.shalom_backend_app.tariff.domain.model;

import java.time.LocalDate;

import com.shalom.shalom_backend_app.route.domain.model.Route;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tariff {
    private Long id;
    private Route route;
    private Double basePrice;
    private Double pricePerKg;
    private Double pricePerKm;
    private LocalDate effectiveDate;
}
