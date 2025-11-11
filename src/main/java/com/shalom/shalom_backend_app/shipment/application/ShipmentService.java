package com.shalom.shalom_backend_app.shipment.application;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.shalom.shalom_backend_app.route.domain.model.Route;
import com.shalom.shalom_backend_app.service.domain.model.Services;
import com.shalom.shalom_backend_app.shipment.domain.model.Package;
import com.shalom.shalom_backend_app.shipment.domain.model.Shipment;
import com.shalom.shalom_backend_app.shipment.domain.ports.in.ManageShipmentUseCase;
import com.shalom.shalom_backend_app.shipment.domain.ports.out.ShipmentRepositoryPort;
import com.shalom.shalom_backend_app.tariff.domain.model.Tariff;
import com.shalom.shalom_backend_app.tariff.domain.ports.out.TariffRepositoryPort;

@Service
public class ShipmentService implements ManageShipmentUseCase {
    
    private final ShipmentRepositoryPort shipmentRepositoryPort;
    private final TariffRepositoryPort tariffRepositoryPort;

    public ShipmentService(ShipmentRepositoryPort shipmentRepositoryPort, TariffRepositoryPort tariffRepositoryPort) {
        this.shipmentRepositoryPort = shipmentRepositoryPort;
        this.tariffRepositoryPort = tariffRepositoryPort;
    }

    @Override
    public Shipment createShipment(Shipment shipment) {
        shipment.setCode(UUID.randomUUID().toString());
        shipment.setStatus("EN PROCESO");
        shipment.setDate(LocalDateTime.now());
        
        Route route = shipment.getRoute();
        Package pkg = shipment.getPkg();
        Services service = shipment.getService();

        Tariff tariff = tariffRepositoryPort.findById(route.getId()).orElseThrow(() -> new RuntimeException("Tarifa no encontrada para la ruta especificada."));

        double totalCost = tariff.getBasePrice() 
                + (tariff.getPricePerKm() * route.getDistanceKm())
                + (tariff.getPricePerKg() * pkg.getWeight()
                + service.getBaseCost());

        shipment.setTotalCost(totalCost);

        return shipmentRepositoryPort.save(shipment);
    }

    @Override
    public void deleteShipment(Long id) {
        shipmentRepositoryPort.deleteById(id);
    }

    @Override
    public Optional<Shipment> findShipmentByCode(String code) {
        return shipmentRepositoryPort.findByCode(code);
    }

    @Override
    public Optional<Shipment> findShipmentById(Long id) {
        return shipmentRepositoryPort.findById(id);
    }

    @Override
    public List<Shipment> listShipments() {
        return shipmentRepositoryPort.findAll();
    }

    @Override
    public Shipment updateShipment(Long id, Shipment shipment) {
        Optional<Shipment> existing = shipmentRepositoryPort.findById(id);

        if (existing.isEmpty()) throw new RuntimeException("Envio no encontrado.");

        Shipment ex = existing.get();

        if (shipment.getClient() != null) ex.setClient(shipment.getClient());
        if (shipment.getPkg() != null) ex.setPkg(shipment.getPkg());
        if (shipment.getRoute() != null) ex.setRoute(shipment.getRoute());
        if (shipment.getService() != null) ex.setService(shipment.getService());
        if (shipment.getCode() != null) ex.setCode(shipment.getCode());
        if (shipment.getStatus() != null) ex.setStatus(shipment.getStatus());
        if (shipment.getDate() != null) ex.setDate(shipment.getDate());
        if (shipment.getTotalCost() != null) ex.setTotalCost(shipment.getTotalCost());

        return shipmentRepositoryPort.save(shipment);
    }
}
