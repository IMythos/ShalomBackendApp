package com.shalom.shalom_backend_app.service.infraestructure.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.shalom.shalom_backend_app.service.domain.model.Services;
import com.shalom.shalom_backend_app.service.domain.ports.out.ServiceRepositoryPort;
import com.shalom.shalom_backend_app.service.infraestructure.mapper.ServiceMapper;
import com.shalom.shalom_backend_app.service.infraestructure.persistence.entity.ServiceEntity;
import com.shalom.shalom_backend_app.service.infraestructure.persistence.repository.ServiceRepository;

@Component
public class ServiceRepositoryAdapter implements ServiceRepositoryPort {

    private final ServiceRepository serviceRepository;

    public ServiceRepositoryAdapter(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public void deleteById(Long id) {
        serviceRepository.deleteById(id);
    }

    @Override
    public List<Services> findAll() {
        return serviceRepository.findAll().stream().map(ServiceMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Services> findById(Long id) {
        return serviceRepository.findById(id).map(ServiceMapper::toDomain);
    }

    @Override
    public Services save(Services service) {
        ServiceEntity entity = ServiceMapper.toEntity(service);
        entity = serviceRepository.save(entity);

        return ServiceMapper.toDomain(entity);
    }
    
}
