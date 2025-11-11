package com.shalom.shalom_backend_app.shipment.application;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.shalom.shalom_backend_app.route.domain.model.Route;
import com.shalom.shalom_backend_app.route.domain.ports.out.RouteRepositoryPort;
import com.shalom.shalom_backend_app.service.domain.model.Services;
import com.shalom.shalom_backend_app.service.domain.ports.out.ServiceRepositoryPort;
import com.shalom.shalom_backend_app.shipment.domain.model.Package;
import com.shalom.shalom_backend_app.shipment.domain.model.Shipment;
import com.shalom.shalom_backend_app.shipment.domain.ports.in.ManageShipmentUseCase;
import com.shalom.shalom_backend_app.shipment.domain.ports.out.ShipmentRepositoryPort;
import com.shalom.shalom_backend_app.tariff.domain.model.Tariff;
import com.shalom.shalom_backend_app.tariff.domain.ports.out.TariffRepositoryPort;
import com.shalom.shalom_backend_app.user.domain.model.Client;
import com.shalom.shalom_backend_app.user.domain.model.User;
import com.shalom.shalom_backend_app.user.domain.ports.out.UserRepositoryPort;

@Service
public class ShipmentService implements ManageShipmentUseCase {

    private final ShipmentRepositoryPort shipmentRepositoryPort;
    private final RouteRepositoryPort routeRepositoryPort;
    private final ServiceRepositoryPort serviceRepositoryPort;
    private final TariffRepositoryPort tariffRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;

    public ShipmentService(ShipmentRepositoryPort shipmentRepositoryPort,
            RouteRepositoryPort routeRepositoryPort,
            ServiceRepositoryPort serviceRepositoryPort,
            TariffRepositoryPort tariffRepositoryPort,
            UserRepositoryPort userRepositoryPort) {
        this.shipmentRepositoryPort = shipmentRepositoryPort;
        this.routeRepositoryPort = routeRepositoryPort;
        this.serviceRepositoryPort = serviceRepositoryPort;
        this.tariffRepositoryPort = tariffRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public Shipment createShipment(Shipment shipment) {
        shipment.setCode(UUID.randomUUID().toString());
        shipment.setStatus("EN PROCESO");
        shipment.setDate(LocalDateTime.now());

        User user = userRepositoryPort.findById(shipment.getClient().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado."));

        Route route = routeRepositoryPort.findById(shipment.getRoute().getId())
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada."));
                
        Services service = serviceRepositoryPort.findById(shipment.getService().getId())
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado."));

        Tariff tariff = tariffRepositoryPort.findById(route.getId())
                .orElseThrow(() -> new RuntimeException("Tarifa no encontrada para la ruta especificada."));

        Package pkg = shipment.getPkg();
        
        double totalCost = tariff.getBasePrice()
                + (tariff.getPricePerKm() * route.getDistanceKm())
                + (tariff.getPricePerKg() * pkg.getWeight()
                        + service.getBaseCost());

        shipment.setClient((Client) user);
        shipment.setRoute(route);
        shipment.setService(service);
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
        Shipment existing = shipmentRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Envio no encontrado."));

        if (shipment.getStatus() != null) {
            existing.setStatus(shipment.getStatus());
        }

        return shipmentRepositoryPort.save(existing);
    }
}
