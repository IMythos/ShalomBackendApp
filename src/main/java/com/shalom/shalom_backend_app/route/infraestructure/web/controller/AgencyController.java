package com.shalom.shalom_backend_app.route.infraestructure.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shalom.shalom_backend_app.route.application.AgencyService;
import com.shalom.shalom_backend_app.route.domain.model.Agency;
import com.shalom.shalom_backend_app.route.infraestructure.mapper.AgencyMapper;
import com.shalom.shalom_backend_app.route.infraestructure.web.dto.request.AgencyRequestDTO;
import com.shalom.shalom_backend_app.route.infraestructure.web.dto.response.AgencyResponseDTO;
import com.shalom.shalom_backend_app.shared.api.ApiResponse;

@RestController
@RequestMapping("/api/agencies")
public class AgencyController {
    private final AgencyService agencyService;

    public AgencyController(AgencyService agencyService) {
        this.agencyService = agencyService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<AgencyResponseDTO>> createAgency(@RequestBody AgencyRequestDTO dto) {
        try {
            Agency domain = AgencyMapper.toDomain(dto);
            Agency created = agencyService.createAgency(domain);

            return ResponseEntity
                    .ok(ApiResponse.success("Agencia creada correctamente.", AgencyMapper.toResponseDTO(created)));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error al crear una nueva agencia."));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AgencyResponseDTO>>> listAgencies() {
        try {
            List<AgencyResponseDTO> list = agencyService.listAgencies().stream().map(AgencyMapper::toResponseDTO)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(ApiResponse.success("Listado de agencias.", list));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error al listar agencias."));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<AgencyResponseDTO>> getAgencyById(@RequestParam Long id) {
        try {
            Agency found = agencyService.getAgencyById(id);
            AgencyResponseDTO dto = AgencyMapper.toResponseDTO(found);

            return ResponseEntity.ok(ApiResponse.success("Agencia encontrada correctamente.", dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error al buscar la agencia."));
        }
    }
}
