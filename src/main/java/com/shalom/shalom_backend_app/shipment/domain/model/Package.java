package com.shalom.shalom_backend_app.shipment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Package {
    private Long id;
    private Double weight;
    private Double height;
    private Double length;
    private String description;
}
