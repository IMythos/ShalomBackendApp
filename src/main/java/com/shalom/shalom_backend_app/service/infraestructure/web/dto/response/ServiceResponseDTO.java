package com.shalom.shalom_backend_app.service.infraestructure.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponseDTO {
    private Long id;
    private String serviceName;
    private String description;
    private Double baseCost;
    private Integer estimatedDays;
    private Boolean isActive;
}
