package com.shalom.shalom_backend_app.shipment.infraestructure.mapper;

import com.shalom.shalom_backend_app.route.domain.model.Route;
import com.shalom.shalom_backend_app.route.infraestructure.mapper.RouteMapper;
import com.shalom.shalom_backend_app.service.domain.model.Services;
import com.shalom.shalom_backend_app.service.infraestructure.mapper.ServiceMapper;
import com.shalom.shalom_backend_app.shipment.domain.model.Package;
import com.shalom.shalom_backend_app.shipment.domain.model.Shipment;
import com.shalom.shalom_backend_app.shipment.infraestructure.persistence.entity.ShipmentEntity;
import com.shalom.shalom_backend_app.shipment.infraestructure.web.dto.request.ShipmentRequestDTO;
import com.shalom.shalom_backend_app.shipment.infraestructure.web.dto.response.ShipmentResponseDTO;
import com.shalom.shalom_backend_app.user.domain.model.Client;
import com.shalom.shalom_backend_app.user.infraestructure.mapper.UserMapper;

public class ShipmentMapper {
    // Domain -> Entity
    public static ShipmentEntity toEntity(Shipment domain) {
        if (domain == null) return null;

        ShipmentEntity entity = new ShipmentEntity();

        entity.setId(domain.getId());
        entity.setClient(UserMapper.toClientEntity(domain.getClient()));
        entity.setPkg(PackageMapper.toEntity(domain.getPkg()));
        entity.setRoute(RouteMapper.toEntity(domain.getRoute()));
        entity.setService(ServiceMapper.toEntity(domain.getService()));
        entity.setCode(domain.getCode());
        entity.setStatus(domain.getStatus());
        entity.setDate(domain.getDate());
        entity.setTotalCost(domain.getTotalCost());

        return entity;
    }

    // Entity -> Domain
    public static Shipment toDomain(ShipmentEntity entity) {
        if (entity == null) return null;

        Shipment domain = new Shipment();

        domain.setId(entity.getId());
        domain.setClient(UserMapper.toClientDomain(entity.getClient()));
        domain.setPkg(PackageMapper.toDomain(entity.getPkg()));
        domain.setRoute(RouteMapper.toDomain(entity.getRoute()));
        domain.setService(ServiceMapper.toDomain(entity.getService()));
        domain.setCode(entity.getCode());
        domain.setStatus(entity.getStatus());
        domain.setDate(entity.getDate());
        domain.setTotalCost(entity.getTotalCost());

        return domain;
    }

    // DTO -> Domain
    public static Shipment toDomain(ShipmentRequestDTO dto) {
        if (dto == null) return null;

        Shipment domain = new Shipment();
        Client client = Client.builder().id(dto.getClientId()).build();

        domain.setClient(client);
        domain.setPkg(new Package(dto.getPackageId()));
        domain.setRoute(new Route(dto.getRouteId()));
        domain.setService(new Services(dto.getServiceId()));

        return domain;
    }

    // Domain -> DTO
    public static ShipmentResponseDTO toResponseDTO(Shipment domain) {
        if (domain == null) return null;

        ShipmentResponseDTO dto = new ShipmentResponseDTO();
    
        dto.setId(domain.getId());
        dto.setClientName(domain.getClient().getUsername());
        dto.setRoute(domain.getRoute().getOrigin() + " - " + domain.getRoute().getDestination());
        dto.setService(domain.getService().getServiceName());
        dto.setCode(domain.getCode());
        dto.setTotalCost(domain.getTotalCost());
        dto.setStatus(domain.getStatus());
        dto.setDate(domain.getDate());

        return dto;
    }
}
