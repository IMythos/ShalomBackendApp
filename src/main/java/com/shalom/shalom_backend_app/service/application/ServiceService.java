package com.shalom.shalom_backend_app.service.application;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shalom.shalom_backend_app.service.domain.model.Services;
import com.shalom.shalom_backend_app.service.domain.ports.in.ManageServiceUseCase;
import com.shalom.shalom_backend_app.service.domain.ports.out.ServiceRepositoryPort;

@Service
public class ServiceService implements ManageServiceUseCase {
    
    private final ServiceRepositoryPort serviceRepositoryPort;

    public ServiceService(ServiceRepositoryPort serviceRepositoryPort) {
        this.serviceRepositoryPort = serviceRepositoryPort;
    }

    @Override
    public Services createService(Services service) {
        return serviceRepositoryPort.save(service);
    }

    @Override
    public void deleteService(Long id) {
        serviceRepositoryPort.deleteById(id);
    }

    @Override
    public Optional<Services> findServiceById(Long id) {
        return serviceRepositoryPort.findById(id);
    }

    @Override
    public List<Services> listServices() {
        return serviceRepositoryPort.findAll();
    }

    @Override
    public Services updateService(Long id, Services service) {
        Optional<Services> existing = serviceRepositoryPort.findById(id);

        if (existing.isEmpty()) {
            throw new RuntimeException("Servicio no especificado: " + id);
        }

        Services ex = existing.get();

        if (service.getServiceName() != null) ex.setServiceName(service.getServiceName());
        if (service.getDescription() != null) ex.setDescription(service.getDescription());
        if (service.getBaseCost() != null) ex.setBaseCost(service.getBaseCost());
        if (service.getEstimatedDays() != null) ex.setEstimatedDays(service.getEstimatedDays());
        if (service.getIsActive() != null) ex.setIsActive(service.getIsActive());

        return serviceRepositoryPort.save(ex);
    }
}
