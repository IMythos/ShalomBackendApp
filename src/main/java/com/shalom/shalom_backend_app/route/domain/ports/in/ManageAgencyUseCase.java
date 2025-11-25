package com.shalom.shalom_backend_app.route.domain.ports.in;

import java.util.List;

import com.shalom.shalom_backend_app.route.domain.model.Agency;

public interface ManageAgencyUseCase {
    Agency createAgency(Agency agency);
    Agency getAgencyById(Long id);
    List<Agency> listAgencies();
}
