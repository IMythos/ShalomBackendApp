package com.shalom.shalom_backend_app.invoice.infraestructure.mapper;

import com.shalom.shalom_backend_app.invoice.domain.model.Invoice;
import com.shalom.shalom_backend_app.invoice.infraestructure.persistence.entity.InvoiceEntity;
import com.shalom.shalom_backend_app.invoice.infraestructure.web.dto.request.InvoiceRequestDTO;
import com.shalom.shalom_backend_app.invoice.infraestructure.web.dto.response.InvoiceResponseDTO;
import com.shalom.shalom_backend_app.shipment.infraestructure.mapper.ShipmentMapper;
import com.shalom.shalom_backend_app.shipment.infraestructure.persistence.entity.ShipmentEntity;
import com.shalom.shalom_backend_app.user.infraestructure.mapper.UserMapper;
import com.shalom.shalom_backend_app.user.infraestructure.persistence.entity.UserEntity;


public class InvoiceMapper {

    public static Invoice toDomain(InvoiceRequestDTO dto, ShipmentEntity shipmentEntity, UserEntity userEntity) {
        if (dto == null) return null;
        
        Invoice invoice = new Invoice();
        invoice.setId(null);
        invoice.setNumber(dto.getNumber());
        invoice.setDate(dto.getDate());
        invoice.setTotalAmount(dto.getTotalAmount());

        invoice.setShipment(ShipmentMapper.toDomain(shipmentEntity));
        invoice.setIssuedBy(UserMapper.toDomain(userEntity));

        return invoice;
    }

    public static InvoiceEntity toEntity(Invoice domain) {
        if (domain == null) return null;

        InvoiceEntity entity = new InvoiceEntity();
        entity.setId(domain.getId());
        entity.setNumber(domain.getNumber());
        entity.setDate(domain.getDate());
        entity.setTotalAmount(domain.getTotalAmount());

        entity.setShipment(ShipmentMapper.toEntity(domain.getShipment()));
        entity.setIssuedBy(UserMapper.toEntity(domain.getIssuedBy()));

        return entity;
    }

    public static Invoice toDomain(InvoiceEntity entity) {
        if (entity == null) return null;

        Invoice domain = new Invoice();
        domain.setId(entity.getId());
        domain.setNumber(entity.getNumber());
        domain.setDate(entity.getDate());
        domain.setTotalAmount(entity.getTotalAmount());

        domain.setShipment(ShipmentMapper.toDomain(entity.getShipment()));
        domain.setIssuedBy(UserMapper.toDomain(entity.getIssuedBy()));

        return domain;
    }

    public static InvoiceResponseDTO toResponseDTO(Invoice domain) {
        if (domain == null) return null;

        InvoiceResponseDTO dto = new InvoiceResponseDTO();
        dto.setId(domain.getId());
        dto.setNumber(domain.getNumber());
        dto.setDate(domain.getDate());
        dto.setTotalAmount(domain.getTotalAmount());

        dto.setShipment(ShipmentMapper.toResponseDTO(domain.getShipment()));
        dto.setIssuedBy(UserMapper.toResponseDTO(domain.getIssuedBy()));

        return dto;
    }
}
