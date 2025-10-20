package com.shalom.shalom_backend_app.route.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.shalom.shalom_backend_app.route.domain.model.Route;

public interface RouteRepositoryPort {
    Route save(Route route);
    Optional<Route> findById(Long id);
    List<Route> findAll();
    void deleteById(Long id);
}
