package com.shalom.shalom_backend_app.invoice.infraestructure.web.dto.response;

import java.time.LocalDateTime;

import com.shalom.shalom_backend_app.shipment.infraestructure.web.dto.response.ShipmentResponseDTO;
import com.shalom.shalom_backend_app.user.infraestructure.web.dto.user.UserResponseDTO;

import lombok.Data;

@Data
public class InvoiceResponseDTO {
    private Long id;
    private String number;
    private LocalDateTime date;
    private Double totalAmount;

    private ShipmentResponseDTO shipment;
    private UserResponseDTO issuedBy;
}
