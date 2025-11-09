package com.shalom.shalom_backend_app.shipment.infraestructure.mapper;

import com.shalom.shalom_backend_app.shipment.domain.model.Package;
import com.shalom.shalom_backend_app.shipment.infraestructure.persistence.entity.PackageEntity;
import com.shalom.shalom_backend_app.shipment.infraestructure.web.dto.request.PackageRequestDTO;
import com.shalom.shalom_backend_app.shipment.infraestructure.web.dto.response.PackageResponseDTO;

public class PackageMapper {
    // Domain -> Entity
    public static PackageEntity toEntity(Package domain) {
        if (domain == null) return null;

        PackageEntity entity = new PackageEntity();

        entity.setId(domain.getId());
        entity.setWeight(domain.getWeight());
        entity.setHeight(domain.getHeight());
        entity.setLength(domain.getLength());
        entity.setDescription(domain.getDescription());

        return entity;
    }

    // Entity -> Domain
    public static Package toDomain(PackageEntity entity) {
        if (entity == null) return null;

        Package domain = new Package();

        domain.setId(entity.getId());
        domain.setWeight(entity.getWeight());
        domain.setHeight(entity.getHeight());
        domain.setLength(entity.getLength());
        domain.setDescription(entity.getDescription());

        return domain;
    }

    // RequestDTO -> Domain
    public static Package toDomain(PackageRequestDTO dto) {
        if (dto == null) return null;

        Package domain = new Package();

        domain.setId(dto.getId());
        domain.setWeight(dto.getWeight());
        domain.setHeight(dto.getHeight());
        domain.setLength(dto.getLength());
        domain.setDescription(dto.getDescription());

        return domain;
    }

    // Domain -> RequestDTO
    public static PackageResponseDTO toResponseDTO(Package domain) {
        if (domain == null) return null;

        PackageResponseDTO dto = new PackageResponseDTO();

        dto.setId(domain.getId());
        dto.setWeight(domain.getWeight());
        dto.setHeight(domain.getHeight());
        dto.setLength(domain.getLength());
        dto.setDescription(domain.getDescription());

        return dto;
    }
}
