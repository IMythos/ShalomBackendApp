package com.shalom.shalom_backend_app.shipment_request.infraestructure.web.dto.response;

import java.time.LocalDateTime;

import com.shalom.shalom_backend_app.shipment_request.domain.model.RequestStatus;

import lombok.Data;

@Data
public class SolicitudeResponseDTO {
    private Long id;
    private Long clientId;
    private String clientUsername;
    private String recipientDni;
    private String recipientName;
    private String recipientCity;
    private String destinationCity;
    private String description;
    private String packageImageUrl;
    private String requestStatus;
    private RequestStatus status;
    private LocalDateTime requestDate;
}
