package com.shalom.shalom_backend_app.tariff.infraestructure.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shalom.shalom_backend_app.shared.api.ApiResponse;
import com.shalom.shalom_backend_app.tariff.application.TariffService;
import com.shalom.shalom_backend_app.tariff.domain.model.Tariff;
import com.shalom.shalom_backend_app.tariff.infraestructure.mapper.TariffMapper;
import com.shalom.shalom_backend_app.tariff.infraestructure.web.dto.request.TariffRequestDTO;
import com.shalom.shalom_backend_app.tariff.infraestructure.web.dto.response.TariffResponseDTO;

@RestController
@RequestMapping("/api/tariffs")
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

    // CUS04.2: Listar tarifas
    @GetMapping
    public ResponseEntity<ApiResponse<List<TariffResponseDTO>>> listTariffs() {
        try {
            List<TariffResponseDTO> tariffs = tariffService.listTariffs()
                    .stream()
                    .map(TariffMapper::toResponseDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(ApiResponse.success("Tarifas listadas correctamente.", tariffs));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error a listar las tarifas."));
        }
    }

    // CUS04.3: Actualizar tarifa
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<TariffResponseDTO>> updateTariff(@PathVariable Long id, @RequestBody TariffRequestDTO dto) {
        try {
            Tariff domain = TariffMapper.toDomain(dto);
            Tariff updated = tariffService.updateTariff(id, domain);

            return ResponseEntity.ok(ApiResponse.success("Tarifa actualizada correctamente.", TariffMapper.toResponseDTO(updated)));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error al actualizar la tarifa."));
        }
    }

    // CUS04.4: Eliminar tarifa
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<Void>> deleteTariff(@RequestParam Long id) {
        try {
            tariffService.deleteTariff(id);
            return ResponseEntity.ok(ApiResponse.success("Tarifa eliminada correctamente.", null));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error al eliminar tarifa."));
        }
    } 
}
