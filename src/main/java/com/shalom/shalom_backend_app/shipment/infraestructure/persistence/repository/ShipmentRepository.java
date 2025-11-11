package com.shalom.shalom_backend_app.shipment.infraestructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.shalom.shalom_backend_app.shipment.infraestructure.persistence.entity.ShipmentEntity;

@Component
public interface ShipmentRepository extends JpaRepository<ShipmentEntity, Long> {
    Optional<ShipmentEntity> findByCode(String code);
}
