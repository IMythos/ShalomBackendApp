package com.shalom.shalom_backend_app.shipment.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.shalom.shalom_backend_app.shipment.domain.model.Shipment;

public interface ShipmentRepositoryPort {
    Shipment save(Shipment shipment);
    Optional<Shipment> findById(Long id);
    Optional<Shipment> findByCode(String code);
    List<Shipment> findAll();
    void deleteById(Long id);
    List<Shipment> findByClientEmail(String email);
}
