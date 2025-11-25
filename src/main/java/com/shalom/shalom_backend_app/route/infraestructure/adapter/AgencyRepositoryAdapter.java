package com.shalom.shalom_backend_app.route.infraestructure.adapter;

import org.springframework.stereotype.Component;

import com.shalom.shalom_backend_app.route.domain.model.Agency;
import com.shalom.shalom_backend_app.route.domain.ports.out.AgencyRepositoryPort;
import com.shalom.shalom_backend_app.route.infraestructure.mapper.AgencyMapper;
import com.shalom.shalom_backend_app.route.infraestructure.persistence.entity.AgencyEntity;
import com.shalom.shalom_backend_app.route.infraestructure.persistence.repository.AgencyRepository;

@Component
public class AgencyRepositoryAdapter implements AgencyRepositoryPort {
    private final AgencyRepository agencyRepository;

    public AgencyRepositoryAdapter(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    @Override
    public Agency findById(Long id) {
        return agencyRepository.findById(id)
                .map(AgencyMapper::toDomain)
                .orElse(null);
    }

    @Override
    public Agency save(Agency agency) {
        AgencyEntity entity = AgencyMapper.toEntity(agency);
        entity = agencyRepository.save(entity);

        return AgencyMapper.toDomain(entity);
    }
}
