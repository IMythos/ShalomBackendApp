package com.shalom.shalom_backend_app.route.infraestructure.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shalom.shalom_backend_app.route.application.RouteService;
import com.shalom.shalom_backend_app.route.infraestructure.web.dto.response.RouteResponseDTO;
import com.shalom.shalom_backend_app.shared.api.ApiResponse;

@RestController
@RequestMapping("/api/routes")
@Validated
public class RouteController {
    
    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    // CUS03: Gestionar tarifas y rutas
    // CUS03.1: Listar rutas
    @GetMapping    
    public ResponseEntity<ApiResponse<List<RouteResponseDTO>>> list() {
        try {
            var list = routeService.listRoute().stream().map(route ->
                new RouteResponseDTO(route.getId(), route.getOrigin(), route.getDestination(), route.getDistanceKm(), route.getEstimatedTime())
            ).collect(Collectors.toList());

            return ResponseEntity.ok(ApiResponse.success("Listado de rutas", list));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error al listar rutas."));
        }
    }
}
