package com.shalom.shalom_backend_app.route.domain.ports.in;

import com.shalom.shalom_backend_app.route.domain.model.Agency;

public interface ManageAgencyUseCase {
    Agency createAgency(Agency agency);
    Agency getAgencyById(Long id);
}
