package com.shalom.shalom_backend_app.shipment.infraestructure.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageResponseDTO {
    private Long id;
    private Double weight;
    private Double height;
    private Double length;
    private String description;
}
