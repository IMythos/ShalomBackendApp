package com.shalom.shalom_backend_app.route.domain.ports.out;

import com.shalom.shalom_backend_app.route.domain.model.Agency;

public interface AgencyRepositoryPort {
    Agency save(Agency agency);
    Agency findById(Long id);
}
