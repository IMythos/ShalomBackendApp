package com.shalom.shalom_backend_app.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Services {
    private Long id;
    private String serviceName;
    private String description;
    private Double baseCost;
    private Integer estimatedDays;
    private Boolean isActive;
}
