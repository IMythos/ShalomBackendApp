package com.shalom.shalom_backend_app.tariff.infraestructure.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shalom.shalom_backend_app.shared.api.ApiResponse;
import com.shalom.shalom_backend_app.tariff.application.TariffService;
import com.shalom.shalom_backend_app.tariff.domain.model.Tariff;
import com.shalom.shalom_backend_app.tariff.infraestructure.mapper.TariffMapper;
import com.shalom.shalom_backend_app.tariff.infraestructure.web.dto.request.TariffRequestDTO;
import com.shalom.shalom_backend_app.tariff.infraestructure.web.dto.response.TariffResponseDTO;

@RestController
@RequestMapping("/api/tariffs")
@Validated
public class TariffController {

    private final TariffService tariffService;

    public TariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    // CUS04: Gestionar tarifas

    // CUS04.1: Crear tarifa
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<TariffResponseDTO>> createTariff(@RequestBody TariffRequestDTO dto) {
        try {
            Tariff domain = TariffMapper.toDomain(dto);
            Tariff created = tariffService.createTariff(domain);

            return ResponseEntity.ok(ApiResponse.success("Tarifa creada correctamente.", TariffMapper.toResponseDTO(created)));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error al crear tarifa."));
        }
    }
}
