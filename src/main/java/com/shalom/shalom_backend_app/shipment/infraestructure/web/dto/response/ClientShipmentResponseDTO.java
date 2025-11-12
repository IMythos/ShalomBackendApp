package com.shalom.shalom_backend_app.shipment.infraestructure.web.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientShipmentResponseDTO {
    private Long id;
    private String code;
    private double totalCost;
    private String status;
    private LocalDateTime date;
    private String route;
    private String service;
    private String packageDescription;
}
