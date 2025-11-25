package com.shalom.shalom_backend_app.route.infraestructure.mapper;

import com.shalom.shalom_backend_app.route.domain.model.Agency;
import com.shalom.shalom_backend_app.route.infraestructure.persistence.entity.AgencyEntity;
import com.shalom.shalom_backend_app.route.infraestructure.web.dto.request.AgencyRequestDTO;
import com.shalom.shalom_backend_app.route.infraestructure.web.dto.response.AgencyResponseDTO;

public class AgencyMapper {
    public static AgencyEntity toEntity(Agency agency) {
        if (agency == null)
            return null;

        AgencyEntity entity = new AgencyEntity();
        entity.setId(agency.getId());
        entity.setName(agency.getName());
        entity.setCity(agency.getCity());
        entity.setAddress(agency.getAddress());

        return entity;
    }

    public static Agency toDomain(AgencyEntity entity) {
        if (entity == null)
            return null;

        Agency agency = new Agency();
        agency.setId(entity.getId());
        agency.setName(entity.getName());
        agency.setCity(entity.getCity());
        agency.setAddress(entity.getAddress());
        
        return agency;
    }

    public static Agency toDomain(AgencyRequestDTO dto) {
        if (dto == null) return null;

        Agency agency = new Agency();

        agency.setId(dto.getId());
        agency.setName(dto.getName());
        agency.setCity(dto.getCity());
        agency.setAddress(dto.getAddress());

        return agency;
    }

    public static AgencyResponseDTO toResponseDTO(Agency domain) {
        if (domain == null) return null;

        AgencyResponseDTO dto = new AgencyResponseDTO();

        dto.setId(domain.getId());
        dto.setName(domain.getName());
        dto.setCity(domain.getCity());
        dto.setAddress(domain.getAddress());

        return dto;
    }
}
