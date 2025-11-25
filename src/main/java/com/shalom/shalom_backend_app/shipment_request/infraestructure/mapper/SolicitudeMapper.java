package com.shalom.shalom_backend_app.shipment_request.infraestructure.mapper;

import com.shalom.shalom_backend_app.shipment_request.domain.model.Solicitude;
import com.shalom.shalom_backend_app.shipment_request.infraestructure.persistence.entity.SolicitudeEntity;
import com.shalom.shalom_backend_app.shipment_request.infraestructure.web.dto.request.SolicitudeRequestDTO;
import com.shalom.shalom_backend_app.shipment_request.infraestructure.web.dto.response.SolicitudeResponseDTO;

public class SolicitudeMapper {
    // Domain -> Entity
    public static SolicitudeEntity toEntity(Solicitude domain) {
        if (domain == null)
            return null;

        SolicitudeEntity entity = new SolicitudeEntity();

        entity.setId(domain.getId());

        entity.setRecipientDni(domain.getRecipientDni());
        entity.setRecipientName(domain.getRecipientName());
        entity.setRecipientCity(domain.getRecipientCity());
        entity.setDestinationCity(domain.getDestinationCity());
        entity.setDescription(domain.getDescription());
        entity.setPackageImageUrl(domain.getPackageImageUrl());

        entity.setStatus(domain.getStatus());
        entity.setRequestDateTime(domain.getRequestDateTime());
        entity.setDeleted(domain.isDeleted());

        return entity;
    }

    // Entity -> Domain
    public static Solicitude toDomain(SolicitudeEntity entity) {
        if (entity == null)
            return null;

        Solicitude domain = new Solicitude();

        domain.setId(entity.getId());
        domain.setClientId(
            entity.getClient() != null ? entity.getClient().getId() : null
        );

        domain.setClientUsername(
            entity.getClient() != null ? entity.getClient().getUsername() : null
        );

        domain.setClientFullname(
            entity.getClient() != null ? entity.getClient().getFullname() : null
        );

        domain.setRecipientDni(entity.getRecipientDni());
        domain.setRecipientName(entity.getRecipientName());
        domain.setRecipientCity(entity.getRecipientCity());
        domain.setDestinationCity(entity.getDestinationCity());
        domain.setDescription(entity.getDescription());
        domain.setPackageImageUrl(entity.getPackageImageUrl());

        domain.setStatus(entity.getStatus());
        domain.setRequestDateTime(entity.getRequestDateTime());
        domain.setDeleted(entity.isDeleted());

        return domain;
    }

    // DTO -> Domain
    public static Solicitude toDomain(SolicitudeRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Solicitude domain = new Solicitude();

        domain.setRecipientDni(dto.getRecipientDni());
        domain.setRecipientName(dto.getRecipientName());
        domain.setRecipientCity(dto.getRecipientCity());
        domain.setDestinationCity(dto.getDestinationCity());
        domain.setDescription(dto.getDescription());

        return domain;
    }

    // Domain -> DTO
    public static SolicitudeResponseDTO toResponseDTO(Solicitude domain) {
        if (domain == null) {
            return null;
        }

        SolicitudeResponseDTO dto = new SolicitudeResponseDTO();

        dto.setId(domain.getId());
        dto.setClientId(domain.getClientId());
        dto.setClientFullname(domain.getClientFullname());

        dto.setRecipientDni(domain.getRecipientDni());
        dto.setRecipientName(domain.getRecipientName());
        dto.setRecipientCity(domain.getRecipientCity());

        dto.setDestinationCity(domain.getDestinationCity());
        dto.setDescription(domain.getDescription());
        dto.setPackageImageUrl(domain.getPackageImageUrl());
        
        dto.setStatus(domain.getStatus().name());
        dto.setRequestDate(domain.getRequestDateTime());

        return dto;
    }

}
