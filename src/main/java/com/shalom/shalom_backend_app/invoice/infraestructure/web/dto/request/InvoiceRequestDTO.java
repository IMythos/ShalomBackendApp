package com.shalom.shalom_backend_app.invoice.infraestructure.web.dto.request;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class InvoiceRequestDTO {
    private String number;          // Número de factura
    private LocalDateTime date;     // Fecha de emisión
    private Double totalAmount;     // Monto total

    private Long shipmentId;        // ID del envío asociado
    private Long issuedByUserId;
}
