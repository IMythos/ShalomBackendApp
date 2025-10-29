package com.shalom.shalom_backend_app.service.infraestructure.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shalom.shalom_backend_app.service.application.ServiceService;
import com.shalom.shalom_backend_app.service.domain.model.Services;
import com.shalom.shalom_backend_app.service.infraestructure.mapper.ServiceMapper;
import com.shalom.shalom_backend_app.service.infraestructure.web.dto.request.ServiceRequestDTO;
import com.shalom.shalom_backend_app.service.infraestructure.web.dto.response.ServiceResponseDTO;
import com.shalom.shalom_backend_app.shared.api.ApiResponse;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/services")
public class ServiceController {
    
    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    // CUS05: Gestionar servicios

    // CUS05.1: Crear servicio
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ServiceResponseDTO>> createService(@RequestBody ServiceRequestDTO dto) {
        try {
            Services domain = ServiceMapper.toDomain(dto);
            Services created = serviceService.createService(domain);

            return ResponseEntity.ok(ApiResponse.success("Servicio agregado correctamente.", ServiceMapper.toResponseDTO(created)));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error al crear nuevo servicio."));
        }
    }

    // CUS05.2: Listar servicios
    @GetMapping
    public ResponseEntity<ApiResponse<List<ServiceResponseDTO>>> list() {
        try {
            List<ServiceResponseDTO> list = serviceService.listServices().stream().map(service ->
                new ServiceResponseDTO(service.getId(), service.getServiceName(), service.getDescription(), service.getBaseCost(), service.getEstimatedDays(), service.getIsActive())
            ).collect(Collectors.toList());

            return ResponseEntity.ok(ApiResponse.success("Listado de servicios", list));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error al listar servicios."));
        }
    }

    // CUS05.3: Actualizar servicio
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<ServiceResponseDTO>> updateService(@PathVariable Long id, @RequestBody ServiceRequestDTO dto) {
        try {
            Services domain = ServiceMapper.toDomain(dto);
            Services updated = serviceService.updateService(id, domain);

            return ResponseEntity.ok(ApiResponse.success("Servicio actualizado.", ServiceMapper.toResponseDTO(updated)));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error al listar servicios."));
        }
    }

    // CUS05.4: Buscar servicio por id
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<ServiceResponseDTO>> getServiceById(@RequestParam Long id) {
        try {
            Services found = serviceService.findServiceById(id).orElseThrow(() -> new EntityNotFoundException("No se encontro el servicio buscado."));
            ServiceResponseDTO dto = ServiceMapper.toResponseDTO(found);
            
            return ResponseEntity.ok(ApiResponse.success("Servicio encontrado correctamente.", dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error al buscar el servicio."));
        }
    }
}
