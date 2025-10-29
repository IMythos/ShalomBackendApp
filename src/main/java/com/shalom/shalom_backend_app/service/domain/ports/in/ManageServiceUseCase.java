package com.shalom.shalom_backend_app.service.domain.ports.in;

import java.util.List;
import java.util.Optional;

import com.shalom.shalom_backend_app.service.domain.model.Services;

public interface ManageServiceUseCase {
    Services createService(Services service);
    Services updateService(Long id, Services service);
    void deleteService(Long id);
    List<Services> listServices();
    Optional<Services> findServiceById(Long id);
}
