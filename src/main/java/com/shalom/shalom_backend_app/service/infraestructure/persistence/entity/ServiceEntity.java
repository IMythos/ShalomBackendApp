package com.shalom.shalom_backend_app.service.infraestructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "service")
@AllArgsConstructor
@NoArgsConstructor
public class ServiceEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_service")
    private Long id;

    @Column(name = "service_name", nullable = false, length = 100)
    private String serviceName;

    @Column(nullable = false)
    private String description;

    @Column(name = "base_cost")
    private Double baseCost;

    @Column(name = "estimated_days")
    private Integer estimatedDays;

    @Column(name = "active")
    private Boolean isActive;
}
