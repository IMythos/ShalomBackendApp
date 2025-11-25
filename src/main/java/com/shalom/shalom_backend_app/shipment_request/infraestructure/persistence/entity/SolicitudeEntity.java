package com.shalom.shalom_backend_app.shipment_request.infraestructure.persistence.entity;

import java.time.LocalDateTime;

import com.shalom.shalom_backend_app.shipment_request.domain.model.RequestStatus;
import com.shalom.shalom_backend_app.user.infraestructure.persistence.entity.ClientEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "solicitude")
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client", nullable = false)
    private ClientEntity client;

    @Column(name = "recipient_dni", length = 8)
    private String recipientDni;

    @Column(name = "recipient_name", nullable = false)
    private String recipientName;

    @Column(name = "recipient_city", length = 150)
    private String recipientCity;

    @Column(name = "destination_city", length = 150)
    private String destinationCity;

    private String description;

    @Column(name = "package_image_url", columnDefinition = "VARCHAR(500)")
    private String packageImageUrl;

    @Column(length = 20, nullable = false)
    private RequestStatus status;

    @Column(name = "request_date", nullable = false)
    private LocalDateTime requestDateTime;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;
}
