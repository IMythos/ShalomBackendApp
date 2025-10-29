package com.shalom.shalom_backend_app.service.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.shalom.shalom_backend_app.service.domain.model.Services;

public interface ServiceRepositoryPort {
    Services save(Services service);
    Optional<Services> findById(Long id);
    List<Services> findAll();
    void deleteById(Long id);
}
