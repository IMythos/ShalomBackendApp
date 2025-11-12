package com.shalom.shalom_backend_app.shipment.infraestructure.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.shalom.shalom_backend_app.shipment.domain.model.Shipment;
import com.shalom.shalom_backend_app.shipment.domain.ports.out.ClientShipmentRepositoryPort;
import com.shalom.shalom_backend_app.shipment.infraestructure.mapper.ShipmentMapper;
import com.shalom.shalom_backend_app.shipment.infraestructure.persistence.repository.ShipmentRepository;

@Component
public class ClientShipmentRepositoryAdapter implements ClientShipmentRepositoryPort {
    
    private final ShipmentRepository shipmentRepository;

    public ClientShipmentRepositoryAdapter(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public List<Shipment> findByClientId(Long id) {
        return shipmentRepository.findByClientId(id).stream().map(ShipmentMapper::toDomain).toList();
    }

    @Override
    public Optional<Shipment> findByCodeAndClientId(String code, Long id) {
        return shipmentRepository.findByCodeAndClientId(code, id).map(ShipmentMapper::toDomain);
    }
}
