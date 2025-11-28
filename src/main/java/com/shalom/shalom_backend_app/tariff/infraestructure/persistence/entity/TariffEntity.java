package com.shalom.shalom_backend_app.tariff.infraestructure.persistence.entity;

import java.time.LocalDate;

import com.shalom.shalom_backend_app.route.infraestructure.persistence.entity.RouteEntity;

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
@Table(name = "tariff")
@AllArgsConstructor
@NoArgsConstructor
public class TariffEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tariff")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_route", nullable = false)
    private RouteEntity route;

    @Column(name = "base_price", nullable = false)
    private Double basePrice;

    @Column(name = "price_per_kg", nullable = false)
    private Double pricePerKg;

    @Column(name = "price_per_km")
    private Double pricePerKm;

    @Column(name = "effective_date")
    private LocalDate effectiveDate;
}
