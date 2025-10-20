package com.shalom.shalom_backend_app.route.infraestructure.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteResponseDTO {
    private Long id;
    private String origin;
    private String destination;
    private Double distanceKm;
    private String estimatedTime;
}
