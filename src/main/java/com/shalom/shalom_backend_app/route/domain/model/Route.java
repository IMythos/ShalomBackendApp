package com.shalom.shalom_backend_app.route.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {
    private Long id;
    private String origin;
    private String destination;
    private Double distanceKm;
    private String estimatedTime;
}
