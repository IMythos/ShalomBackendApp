package com.shalom.shalom_backend_app.service.infraestructure.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shalom.shalom_backend_app.service.application.ServiceService;
import com.shalom.shalom_backend_app.service.domain.model.Services;
import com.shalom.shalom_backend_app.service.infraestructure.mapper.ServiceMapper;
import com.shalom.shalom_backend_app.service.infraestructure.web.dto.request.ServiceRequestDTO;
import com.shalom.shalom_backend_app.service.infraestructure.web.dto.response.ServiceResponseDTO;
import com.shalom.shalom_backend_app.shared.api.ApiResponse;

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
}
