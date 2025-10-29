package com.shalom.shalom_backend_app.service.infraestructure.mapper;

import com.shalom.shalom_backend_app.service.domain.model.Services;
import com.shalom.shalom_backend_app.service.infraestructure.persistence.entity.ServiceEntity;
import com.shalom.shalom_backend_app.service.infraestructure.web.dto.request.ServiceRequestDTO;
import com.shalom.shalom_backend_app.service.infraestructure.web.dto.response.ServiceResponseDTO;

public class ServiceMapper {
    // Domain -> Entity
    public static ServiceEntity toEntity(Services domain) {
        if (domain == null) return null;

        ServiceEntity entity = new ServiceEntity();

        entity.setId(domain.getId());
        entity.setServiceName(domain.getServiceName());
        entity.setDescription(domain.getDescription());
        entity.setBaseCost(domain.getBaseCost());
        entity.setEstimatedDays(domain.getEstimatedDays());
        entity.setIsActive(domain.getIsActive());

        return entity;
    }

    // Entity -> Domain
    public static Services toDomain(ServiceEntity entity) {
        if (entity == null) return null;

        Services domain = new Services();

        domain.setId(entity.getId());
        domain.setServiceName(entity.getServiceName());
        domain.setDescription(entity.getDescription());
        domain.setBaseCost(entity.getBaseCost());
        domain.setEstimatedDays(entity.getEstimatedDays());
        domain.setIsActive(entity.getIsActive());

        return domain;
    }

    // RequestDTO -> Domain
    public static Services toDomain(ServiceRequestDTO dto) {
        if (dto == null) return null;

        Services domain = new Services();

        domain.setId(dto.getId());
        domain.setServiceName(dto.getServiceName());
        domain.setDescription(dto.getDescription());
        domain.setBaseCost(dto.getBaseCost());
        domain.setEstimatedDays(dto.getEstimatedDays());
        domain.setIsActive(dto.getIsActive());

        return domain;
    }

    // Domain -> ResponseDTO
    public static ServiceResponseDTO toResponseDTO(Services domain) {
        if (domain == null) return null;

        ServiceResponseDTO dto = new ServiceResponseDTO();

        dto.setId(domain.getId());
        dto.setServiceName(domain.getServiceName());
        dto.setDescription(domain.getDescription());
        dto.setBaseCost(domain.getBaseCost());
        dto.setEstimatedDays(domain.getEstimatedDays());
        dto.setIsActive(domain.getIsActive());

        return dto;
    }
}
