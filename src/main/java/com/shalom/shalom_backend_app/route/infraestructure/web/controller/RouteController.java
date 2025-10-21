package com.shalom.shalom_backend_app.route.infraestructure.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shalom.shalom_backend_app.route.application.RouteService;
import com.shalom.shalom_backend_app.route.domain.model.Route;
import com.shalom.shalom_backend_app.route.infraestructure.mapper.RouteMapper;
import com.shalom.shalom_backend_app.route.infraestructure.web.dto.request.RouteRequestDTO;
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

    // CUS03.1: Crear ruta
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<RouteResponseDTO>> createRoute(@RequestBody RouteRequestDTO dto) {
        try {
            Route domain = RouteMapper.toDomain(dto);
            Route created = routeService.createRoute(domain);

            return ResponseEntity.ok(ApiResponse.success("Ruta agregada correctamente.", RouteMapper.toResponseDTO(created)));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error al crear una nueva ruta."));
        }
    }

    // CUS03.2: Listar rutas
    @GetMapping    
    public ResponseEntity<ApiResponse<List<RouteResponseDTO>>> list() {
        try {
            List<RouteResponseDTO> list = routeService.listRoute().stream().map(route ->
                new RouteResponseDTO(route.getId(), route.getOrigin(), route.getDestination(), route.getDistanceKm(), route.getEstimatedTime())
            ).collect(Collectors.toList());

            return ResponseEntity.ok(ApiResponse.success("Listado de rutas", list));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error al listar rutas."));
        }
    }

    // CUS03.3: Actualizar ruta
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<RouteResponseDTO>> updateRoute(@RequestParam Long id, @RequestBody RouteRequestDTO dto) {
        try {
            Route domain = RouteMapper.toDomain(dto);
            Route updated = routeService.updateRoute(id, domain);

            return ResponseEntity.ok(ApiResponse.success("Ruta actualizada correctamente.", RouteMapper.toResponseDTO(updated)));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error al modificar ruta."));
        }
    }

    // CUS03.4: Eliminar ruta
    public ResponseEntity<ApiResponse<Void>> deleteRoute(@RequestParam Long id) {
        try {
            routeService.deleteRoute(id);
            return ResponseEntity.ok(ApiResponse.success("Ruta eliminada correctamente.", null));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error al eliminar una ruta."));
        }
    }
}
