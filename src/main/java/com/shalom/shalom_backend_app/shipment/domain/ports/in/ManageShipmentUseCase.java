package com.shalom.shalom_backend_app.shipment.domain.ports.in;

import java.util.List;
import java.util.Optional;

import com.shalom.shalom_backend_app.shipment.domain.model.Shipment;

public interface ManageShipmentUseCase {
    Shipment createShipment(Shipment shipment);
    Shipment updateShipment(Long id, Shipment shipment);
    void deleteShipment(Long id);
    List<Shipment> listShipments();
    Optional<Shipment> findShipmentById(Long id);
    Optional<Shipment> findShipmentByCode(String code);
}
