package com.shalom.shalom_backend_app.route.infraestructure.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/routes")
@Validated
public class RouteController {
    
    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    // CUS03: Gestionar rutas

    // CUS03.1: Crear ruta (success)
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

    // CUS03.2: Listar rutas (success)
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
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<RouteResponseDTO>> updateRoute(@PathVariable Long id, @RequestBody RouteRequestDTO dto) {
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
    @DeleteMapping("/delete")
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

    // CUS03.5: Buscar ruta por id
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<RouteResponseDTO>> getRouteById(@RequestParam Long id) {
        try {
            Route found = routeService.findRouteById(id).orElseThrow(() -> new EntityNotFoundException("No se encontro la ruta con id: " + id));
            RouteResponseDTO dto = RouteMapper.toResponseDTO(found);
        
            return ResponseEntity.ok(ApiResponse.success("Ruta encontrada correctamente.", dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error al buscar la ruta."));
        }
    }
}
