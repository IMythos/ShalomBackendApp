package com.shalom.shalom_backend_app.invoice.domain.model;

import java.time.LocalDateTime;

import com.shalom.shalom_backend_app.shipment.domain.model.Shipment;
import com.shalom.shalom_backend_app.user.domain.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    private Long id;
    private String number;
    private Shipment shipment;
    private User issuedBy;
    private LocalDateTime date;
    private Double totalAmount;
}
