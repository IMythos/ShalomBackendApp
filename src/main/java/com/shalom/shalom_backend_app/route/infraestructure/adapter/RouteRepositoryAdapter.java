package com.shalom.shalom_backend_app.route.infraestructure.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.shalom.shalom_backend_app.route.domain.model.Route;
import com.shalom.shalom_backend_app.route.domain.ports.out.RouteRepositoryPort;
import com.shalom.shalom_backend_app.route.infraestructure.mapper.RouteMapper;
import com.shalom.shalom_backend_app.route.infraestructure.persistence.entity.RouteEntity;
import com.shalom.shalom_backend_app.route.infraestructure.persistence.repository.RouteRepository;

@Component
public class RouteRepositoryAdapter implements RouteRepositoryPort {

    private final RouteRepository routeRepository;

    public RouteRepositoryAdapter(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public void deleteById(Long id) {
        routeRepository.deleteById(id);
    }

    @Override
    public List<Route> findAll() {
        return routeRepository.findAll().stream().map(RouteMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Route> findById(Long id) {
        return routeRepository.findById(id).map(RouteMapper::toDomain);
    }

    @Override
    public Route save(Route route) {
        RouteEntity entity = RouteMapper.toEntity(route);
        entity = routeRepository.save(entity);

        return RouteMapper.toDomain(entity);
    }
    
}
