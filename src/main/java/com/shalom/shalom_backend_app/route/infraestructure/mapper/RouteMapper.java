package com.shalom.shalom_backend_app.route.infraestructure.mapper;

import com.shalom.shalom_backend_app.route.domain.model.Route;
import com.shalom.shalom_backend_app.route.infraestructure.persistence.entity.RouteEntity;

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
    // Domain -> RequestDTO
}
