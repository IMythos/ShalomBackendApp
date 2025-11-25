package com.shalom.shalom_backend_app.tariff.infraestructure.mapper;

import com.shalom.shalom_backend_app.route.domain.model.Route;
import com.shalom.shalom_backend_app.route.infraestructure.persistence.entity.RouteEntity;
import com.shalom.shalom_backend_app.tariff.domain.model.Tariff;
import com.shalom.shalom_backend_app.tariff.infraestructure.persistence.entity.TariffEntity;
import com.shalom.shalom_backend_app.tariff.infraestructure.web.dto.request.TariffRequestDTO;
import com.shalom.shalom_backend_app.tariff.infraestructure.web.dto.response.TariffResponseDTO;

public class TariffMapper {

    // Dominio -> Entidad

    public static TariffEntity toEntity(Tariff domain) {
        if (domain == null) return null;

        TariffEntity entity = new TariffEntity();

        entity.setId(domain.getId());
        if  (domain.getRoute() != null) {
            RouteEntity routeEntity = new RouteEntity();
            routeEntity.setId(domain.getRoute().getId());
            entity.setRoute(routeEntity);
        }
        entity.setBasePrice(domain.getBasePrice());
        entity.setPricePerKg(domain.getPricePerKg());
        entity.setPricePerKm(domain.getPricePerKm());
        entity.setEffectiveDate(domain.getEffectiveDate());

        return entity;
    }

    // Entity -> Domain

    public static Tariff toDomain(TariffEntity entity) {
        if (entity == null) return null;
        
        Tariff domain = new Tariff();

        domain.setId(entity.getId());
        if (entity.getRoute() != null) {
            Route route = new Route();
            route.setId(entity.getRoute().getId());
            domain.setRoute(route);
        }
        domain.setBasePrice(entity.getBasePrice());
        domain.setPricePerKg(entity.getPricePerKg());
        domain.setPricePerKm(entity.getPricePerKm());
        domain.setEffectiveDate(entity.getEffectiveDate());

        return domain;
    }

    // RequestDTO -> Domain

    public static Tariff toDomain(TariffRequestDTO dto) {
        if (dto == null) return null;

        Tariff domain = new Tariff();

        domain.setId(dto.getId());
        if (dto.getRouteId() != null) {
            Route route = new Route();
            route.setId(dto.getId());
            domain.setRoute(route);
        }
        domain.setBasePrice(dto.getBasePrice());
        domain.setPricePerKg(dto.getPricePerKg());
        domain.setPricePerKm(dto.getPricePerKm());
        domain.setEffectiveDate(dto.getEffectiveDate());

        return domain;
    }

    // Domain -> ResponseDTO

    public static TariffResponseDTO toResponseDTO(Tariff domain) {
        if (domain == null) return null;

        TariffResponseDTO dto = new TariffResponseDTO();

        dto.setId(domain.getId());
        
        if (domain.getRoute() != null) {
            dto.setRouteId(domain.getRoute().getId());

            if (domain.getRoute().getOrigin() != null) {
                dto.setRouteOrigin(domain.getRoute().getOrigin().getName());
            }

            if (domain.getRoute().getDestination() != null) {
                dto.setRouteDestination(domain.getRoute().getDestination().getName());
            }
        }

        dto.setBasePrice(domain.getBasePrice());
        dto.setPricePerKg(domain.getPricePerKg());
        dto.setPricePerKm(domain.getPricePerKm());
        dto.setEffectiveDate(domain.getEffectiveDate());

        return dto;
    }
}
