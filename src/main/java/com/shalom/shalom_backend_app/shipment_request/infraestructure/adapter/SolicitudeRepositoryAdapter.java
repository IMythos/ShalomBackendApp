package com.shalom.shalom_backend_app.shipment_request.infraestructure.adapter;

import org.springframework.stereotype.Component;

import com.shalom.shalom_backend_app.shipment_request.domain.model.Solicitude;
import com.shalom.shalom_backend_app.shipment_request.domain.ports.out.CreateSolicitudeRepositoryPort;
import com.shalom.shalom_backend_app.shipment_request.infraestructure.mapper.SolicitudeMapper;
import com.shalom.shalom_backend_app.shipment_request.infraestructure.persistence.entity.SolicitudeEntity;
import com.shalom.shalom_backend_app.shipment_request.infraestructure.persistence.repository.SolicitudeRepository;

@Component
public class SolicitudeRepositoryAdapter implements CreateSolicitudeRepositoryPort {
    
    private final SolicitudeRepository solicitudeRepository;

    public SolicitudeRepositoryAdapter(SolicitudeRepository solicitudeRepository) {
        this.solicitudeRepository = solicitudeRepository;
    }

    @Override
    public Solicitude save(Solicitude solicitude) {
        SolicitudeEntity entity = SolicitudeMapper.toEntity(solicitude);
        entity = solicitudeRepository.save(entity);

        return SolicitudeMapper.toDomain(entity);
    }
}
