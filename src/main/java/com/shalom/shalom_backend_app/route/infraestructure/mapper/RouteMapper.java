package com.shalom.shalom_backend_app.route.infraestructure.mapper;

import com.shalom.shalom_backend_app.route.domain.model.Route;
import com.shalom.shalom_backend_app.route.infraestructure.persistence.entity.RouteEntity;
import com.shalom.shalom_backend_app.route.infraestructure.web.dto.request.RouteRequestDTO;
import com.shalom.shalom_backend_app.route.infraestructure.web.dto.response.RouteResponseDTO;

public class RouteMapper {

    // Domain -> Entity
    
    public static RouteEntity toEntity(Route route) {
        if (route == null) return null;

        RouteEntity entity = new RouteEntity();

        entity.setId(route.getId());
        entity.setOrigin(route.getOrigin());
        entity.setDestination(route.getDestination());
        entity.setDistanceKm(route.getDistanceKm());
        entity.setEstimatedTime(route.getEstimatedTime());

        return entity;
    }

    // Entity -> Domain

    public static Route toDomain(RouteEntity entity) {
        if (entity == null) return null;

        Route route = new Route();

        route.setId(entity.getId());
        route.setOrigin(entity.getOrigin());
        route.setDestination(entity.getDestination());
        route.setDistanceKm(entity.getDistanceKm());
        route.setEstimatedTime(entity.getEstimatedTime());

        return route;
    }

    // RequestDTO -> Domain 

    public static Route toDomain(RouteRequestDTO dto) {
        if (dto == null) return null;

        Route route = new Route();

        route.setId(dto.getId());
        route.setOrigin(dto.getOrigin());
        route.setDestination(dto.getDestination());
        route.setDistanceKm(dto.getDistanceKm());
        route.setEstimatedTime(dto.getEstimatedTime());

        return route;
    }

    // Domain -> ResponseDTO

    public static RouteResponseDTO toResponseDTO(Route domain) {
        if (domain == null) return null;

        RouteResponseDTO dto = new RouteResponseDTO();

        dto.setId(domain.getId());
        dto.setOrigin(domain.getOrigin());
        dto.setDestination(domain.getDestination());
        dto.setDistanceKm(domain.getDistanceKm());
        dto.setEstimatedTime(domain.getEstimatedTime());

        return dto;
    }
}
