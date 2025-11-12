package com.shalom.shalom_backend_app.shipment.domain.ports.in;

import java.util.List;

import com.shalom.shalom_backend_app.shipment.domain.model.Shipment;

public interface GetClientShipmentUseCase {
    List<Shipment> getShipmentsByClientId(Long id);
    Shipment getShipmentByCodeAndClient(Long id, String code);
}
