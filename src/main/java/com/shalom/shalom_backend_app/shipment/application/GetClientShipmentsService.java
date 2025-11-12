package com.shalom.shalom_backend_app.shipment.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shalom.shalom_backend_app.shipment.domain.model.Shipment;
import com.shalom.shalom_backend_app.shipment.domain.ports.in.GetClientShipmentUseCase;
import com.shalom.shalom_backend_app.shipment.domain.ports.out.ClientShipmentRepositoryPort;

@Service
public class GetClientShipmentsService implements GetClientShipmentUseCase{
    
    private final ClientShipmentRepositoryPort shipmentRepositoryPort;
    
    public GetClientShipmentsService(ClientShipmentRepositoryPort shipmentRepositoryPort) {
        this.shipmentRepositoryPort = shipmentRepositoryPort;
    }

    @Override
    public List<Shipment> getShipmentsByClientId(Long clientId) {
        return shipmentRepositoryPort.findByClientId(clientId);
    }

    @Override
    public Shipment getShipmentByCodeAndClient(Long clientId, String code) {
        return shipmentRepositoryPort.findByCodeAndClientId(code, clientId)
                .orElseThrow(() -> new RuntimeException("Envío no encontrado para el cliente."));
    }
}
