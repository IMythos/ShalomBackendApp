package com.shalom.shalom_backend_app.shipment.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.shalom.shalom_backend_app.shipment.domain.model.Shipment;

public interface ClientShipmentRepositoryPort {
    List<Shipment> findByClientId(Long id);
    Optional<Shipment> findByCodeAndClientId(String code, Long id);
}
