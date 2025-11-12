package com.shalom.shalom_backend_app.shipment.infraestructure.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.shalom.shalom_backend_app.shipment.domain.model.Shipment;
import com.shalom.shalom_backend_app.shipment.domain.ports.out.ShipmentRepositoryPort;
import com.shalom.shalom_backend_app.shipment.infraestructure.mapper.ShipmentMapper;
import com.shalom.shalom_backend_app.shipment.infraestructure.persistence.entity.ShipmentEntity;
import com.shalom.shalom_backend_app.shipment.infraestructure.persistence.repository.ShipmentRepository;

@Component
public class ShipmentRepositoryAdapter implements ShipmentRepositoryPort {
    private final ShipmentRepository shipmentRepository;

    public ShipmentRepositoryAdapter(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public void deleteById(Long id) {
        shipmentRepository.deleteById(id);
    }

    @Override
    public List<Shipment> findAll() {
        return shipmentRepository.findAll().stream().map(ShipmentMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Shipment> findByCode(String code) {
        return shipmentRepository.findByCode(code).map(ShipmentMapper::toDomain);
    }

    @Override
    public Optional<Shipment> findById(Long id) {
        return shipmentRepository.findById(id).map(ShipmentMapper::toDomain);
    }

    @Override
    public Shipment save(Shipment shipment) {
        ShipmentEntity entity = ShipmentMapper.toEntity(shipment);
        entity = shipmentRepository.save(entity);

        return ShipmentMapper.toDomain(entity);
    }

    @Override
    public List<Shipment> findByClientEmail(String email) {
        return shipmentRepository.findByClientEmail(email).stream()
                .map(ShipmentMapper::toDomain)
                .collect(Collectors.toList());
    }

}
