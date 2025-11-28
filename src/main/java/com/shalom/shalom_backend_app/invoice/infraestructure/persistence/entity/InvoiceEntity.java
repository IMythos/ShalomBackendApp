package com.shalom.shalom_backend_app.invoice.infraestructure.persistence.entity;

import java.time.LocalDateTime;

import com.shalom.shalom_backend_app.shipment.infraestructure.persistence.entity.ShipmentEntity;
import com.shalom.shalom_backend_app.user.infraestructure.persistence.entity.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "invoice")
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_invoice")
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String number;

    @ManyToOne
    @JoinColumn(name = "id_shipment", nullable = false)
    private ShipmentEntity shipment;

    @ManyToOne
    @JoinColumn(name = "id_employee", nullable = false)
    private UserEntity issuedBy;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;
}
