package com.shalom.shalom_backend_app.route.domain.ports.in;

import java.util.List;
import java.util.Optional;

import com.shalom.shalom_backend_app.route.domain.model.Route;

public interface ManageRouteUseCase {
    Route createRoute(Route route);
    Route updateRoute(Long id, Route route);
    void deleteRoute(Long id);
    List<Route> listRoute();
    Optional<Route> findRouteById(Long id);
}
