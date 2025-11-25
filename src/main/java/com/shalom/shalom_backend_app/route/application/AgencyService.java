package com.shalom.shalom_backend_app.route.application;

import org.springframework.stereotype.Service;

import com.shalom.shalom_backend_app.route.domain.model.Agency;
import com.shalom.shalom_backend_app.route.domain.ports.in.ManageAgencyUseCase;
import com.shalom.shalom_backend_app.route.domain.ports.out.AgencyRepositoryPort;

@Service
public class AgencyService implements ManageAgencyUseCase {
    
    private final AgencyRepositoryPort agencyRepositoryPort;

    public AgencyService(AgencyRepositoryPort agencyRepositoryPort) {
        this.agencyRepositoryPort = agencyRepositoryPort;
    }

    @Override
    public Agency createAgency(Agency agency) {
        return agencyRepositoryPort.save(agency);
    }

    @Override
    public Agency getAgencyById(Long id) {
        return agencyRepositoryPort.findById(id);
    }
}
