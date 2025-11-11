package com.shalom.shalom_backend_app.shipment.infraestructure.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentStatusUpdateDTO {
    private String status;
}
