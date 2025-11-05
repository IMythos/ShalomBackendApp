package com.shalom.shalom_backend_app.shipment.domain.model;

import java.time.LocalDateTime;

import com.shalom.shalom_backend_app.route.domain.model.Route;
import com.shalom.shalom_backend_app.service.domain.model.Services;
import com.shalom.shalom_backend_app.user.domain.model.Client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {
    private Long id;
    private Client client;
    private Package pkg;
    private Route route;
    private Services service;
    private String code;
    private String status;
    private LocalDateTime date;
    private Double totalCost;
}
