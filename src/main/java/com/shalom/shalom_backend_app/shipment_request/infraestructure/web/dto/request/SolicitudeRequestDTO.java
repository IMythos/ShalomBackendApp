package com.shalom.shalom_backend_app.shipment_request.infraestructure.web.dto.request;

import lombok.Data;

@Data
public class SolicitudeRequestDTO {
    private String recipientDni;
    private String recipientName;
    private String destinationCity;
    private String description;
}
