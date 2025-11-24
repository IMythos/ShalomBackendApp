package com.shalom.shalom_backend_app.shipment_request.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Solicitude {
    private Long id;
    private Long clientId;
    private String recipientDni;
    private String recipientName;
    private String destinationCity;
    private String description;
    private String packageImageUrl;
    private RequestStatus status;
    private LocalDateTime requestDateTime; 
    private boolean isDeleted;
}
