package com.shalom.shalom_backend_app.shipment.infraestructure.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentRequestDTO {
    private Long clientId;
    private Long packageId;
    private Long routeId;   
    private Long serviceId;
}
