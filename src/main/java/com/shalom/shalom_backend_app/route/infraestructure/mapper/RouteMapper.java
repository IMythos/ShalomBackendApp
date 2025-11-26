package com.shalom.shalom_backend_app.route.infraestructure.mapper;

import com.shalom.shalom_backend_app.route.domain.model.Agency;
import com.shalom.shalom_backend_app.route.domain.model.Route;
import com.shalom.shalom_backend_app.route.infraestructure.persistence.entity.RouteEntity;
import com.shalom.shalom_backend_app.route.infraestructure.web.dto.request.RouteRequestDTO;
import com.shalom.shalom_backend_app.route.infraestructure.web.dto.response.AgencyResponseDTO;
import com.shalom.shalom_backend_app.route.infraestructure.web.dto.response.RouteResponseDTO;

public class RouteMapper {

    public static RouteEntity toEntity(Route route) {
        if (route == null) return null;

        RouteEntity entity = new RouteEntity();

        entity.setId(route.getId());
        entity.setOrigin(AgencyMapper.toEntity(route.getOrigin()));
        entity.setDestination(AgencyMapper.toEntity(route.getDestination()));
        entity.setDistanceKm(route.getDistanceKm());
        entity.setEstimatedTime(route.getEstimatedTime());

        return entity;
    }

    public static Route toDomain(RouteEntity entity) {
        if (entity == null) return null;

        Route route = new Route();

        route.setId(entity.getId());
        route.setOrigin(AgencyMapper.toDomain(entity.getOrigin()));
        route.setDestination(AgencyMapper.toDomain(entity.getDestination()));
        route.setDistanceKm(entity.getDistanceKm());
        route.setEstimatedTime(entity.getEstimatedTime());

        return route;
    }

    public static Route toDomain(RouteRequestDTO dto) {
        if (dto == null) return null;

        Route route = new Route();

        route.setId(dto.getId());
        route.setDistanceKm(dto.getDistanceKm());
        route.setEstimatedTime(dto.getEstimatedTime());

        Agency origin = new Agency();
        origin.setId(dto.getOriginId());
        route.setOrigin(origin);

        Agency destination = new Agency();
        destination.setId(dto.getDestinationId());
        route.setDestination(destination);

        return route;
    }

    public static RouteResponseDTO toResponseDTO(Route domain) {
        if (domain == null) return null;

        RouteResponseDTO dto = new RouteResponseDTO();

        dto.setId(domain.getId());
        
        if (domain.getOrigin() != null) {
            AgencyResponseDTO originDTO = new AgencyResponseDTO();

            originDTO.setId(domain.getOrigin().getId());
            originDTO.setName(domain.getOrigin().getName());
            originDTO.setCity(domain.getOrigin().getCity());
            originDTO.setAddress(domain.getOrigin().getAddress());

            dto.setOrigin(originDTO);
        }

        if (domain.getDestination() != null) {
            AgencyResponseDTO destinationDTO = new AgencyResponseDTO();

            destinationDTO.setId(domain.getDestination().getId());
            destinationDTO.setName(domain.getDestination().getName());
            destinationDTO.setCity(domain.getDestination().getCity());
            destinationDTO.setAddress(domain.getDestination().getAddress());

            dto.setDestination(destinationDTO);
        }

        dto.setDistanceKm(domain.getDistanceKm());
        dto.setEstimatedTime(domain.getEstimatedTime());

        return dto;
    }
}
