package com.shalom.shalom_backend_app.shipment_request.infraestructure.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.shalom.shalom_backend_app.shipment_request.domain.model.Solicitude;
import com.shalom.shalom_backend_app.shipment_request.domain.ports.out.CreateSolicitudeRepositoryPort;
import com.shalom.shalom_backend_app.shipment_request.domain.ports.out.ListSolicitudeRepositoryPort;
import com.shalom.shalom_backend_app.shipment_request.infraestructure.mapper.SolicitudeMapper;
import com.shalom.shalom_backend_app.shipment_request.infraestructure.persistence.entity.SolicitudeEntity;
import com.shalom.shalom_backend_app.shipment_request.infraestructure.persistence.repository.SolicitudeRepository;
import com.shalom.shalom_backend_app.user.infraestructure.persistence.entity.ClientEntity;
import com.shalom.shalom_backend_app.user.infraestructure.persistence.entity.UserEntity;
import com.shalom.shalom_backend_app.user.infraestructure.persistence.repository.UserRepository;

@Component
public class SolicitudeRepositoryAdapter implements CreateSolicitudeRepositoryPort, ListSolicitudeRepositoryPort {
    
    private final SolicitudeRepository solicitudeRepository;
    private final UserRepository userRepository;

    public SolicitudeRepositoryAdapter(SolicitudeRepository solicitudeRepository, UserRepository userRepository) {
        this.solicitudeRepository = solicitudeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Solicitude save(Solicitude solicitude) {
        SolicitudeEntity entity = SolicitudeMapper.toEntity(solicitude);
        UserEntity client = userRepository.findById(solicitude.getClientId())
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado."));

        if (!(client instanceof ClientEntity)) {
            throw new RuntimeException("El usuario no es un cliente.");
        }
        
        entity.setClient((ClientEntity) client);
        entity = solicitudeRepository.save(entity);

        return SolicitudeMapper.toDomain(entity);
    }

    @Override
    public List<Solicitude> findAllByClientId(Long id) {
        List<SolicitudeEntity> entities = solicitudeRepository.findByClientId(id);

        return entities.stream().map(SolicitudeMapper::toDomain).collect(Collectors.toList());
    }
    
}
