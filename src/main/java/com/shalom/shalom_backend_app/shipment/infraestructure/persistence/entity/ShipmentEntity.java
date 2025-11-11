package com.shalom.shalom_backend_app.shipment.infraestructure.persistence.entity;

import java.time.LocalDateTime;

import com.shalom.shalom_backend_app.route.infraestructure.persistence.entity.RouteEntity;
import com.shalom.shalom_backend_app.service.infraestructure.persistence.entity.ServiceEntity;
import com.shalom.shalom_backend_app.user.infraestructure.persistence.entity.ClientEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "shipment")
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_shipment")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    private ClientEntity client;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_package", nullable = false)
    private PackageEntity pkg;

    @ManyToOne
    @JoinColumn(name = "id_route", nullable = false)
    private RouteEntity route;

    @ManyToOne
    @JoinColumn(name = "id_service", nullable = false)
    private ServiceEntity service;

    @Column(nullable = false, unique = true)
    private String code;

    private String status;

    private LocalDateTime date;

    @Column(name = "total_cost")
    private Double totalCost;
}
