package com.shalom.shalom_backend_app.shipment_request.infraestructure.web.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SolicitudeResponseDTO {
    private Long id;
    private Long clientId;
    private String clientFullname;

    private String recipientDni;
    private String recipientName;
    private String recipientCity;

    private String destinationCity;
    private String description;
    private String packageImageUrl;
    
    private String status;
    private LocalDateTime requestDate;
}
