package com.shalom.shalom_backend_app.route.application;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shalom.shalom_backend_app.route.domain.model.Agency;
import com.shalom.shalom_backend_app.route.domain.model.Route;
import com.shalom.shalom_backend_app.route.domain.ports.in.ManageRouteUseCase;
import com.shalom.shalom_backend_app.route.domain.ports.out.AgencyRepositoryPort;
import com.shalom.shalom_backend_app.route.domain.ports.out.RouteRepositoryPort;

@Service
public class RouteService implements ManageRouteUseCase {

    private final RouteRepositoryPort routeRepositoryPort;
    private final AgencyRepositoryPort agencyRepositoryPort;

    public RouteService(RouteRepositoryPort routeRepositoryPort, AgencyRepositoryPort agencyRepositoryPort) {
        this.routeRepositoryPort = routeRepositoryPort;
        this.agencyRepositoryPort = agencyRepositoryPort;
    }

    @Override
    public Route createRoute(Route route) {
        Agency origin = agencyRepositoryPort.findById(route.getOrigin().getId());
        Agency destination = agencyRepositoryPort.findById(route.getDestination().getId());

        route.setOrigin(origin);
        route.setDestination(destination);

        return routeRepositoryPort.save(route);
    }

    @Override
    public Route updateRoute(Long id, Route route) {
        Optional<Route> existing = routeRepositoryPort.findById(id);

        if (existing.isEmpty()) {
            throw new RuntimeException("Ruta no especificada: " + id);
        }

        Route ex = existing.get();

        if (route.getOrigin() != null) ex.setOrigin(route.getOrigin());
        if (route.getDestination() != null) ex.setDestination(route.getDestination());
        if (route.getDistanceKm() != null) ex.setDistanceKm(route.getDistanceKm());
        if (route.getEstimatedTime() != null) ex.setEstimatedTime(route.getEstimatedTime());

        return routeRepositoryPort.save(ex);
    }

    @Override
    public void deleteRoute(Long id) {
        routeRepositoryPort.deleteById(id);
    }

    @Override
    public List<Route> listRoute() {
        return routeRepositoryPort.findAll();
    }

    @Override
    public Optional<Route> findRouteById(Long id) {
        return routeRepositoryPort.findById(id);
    }
    
}
