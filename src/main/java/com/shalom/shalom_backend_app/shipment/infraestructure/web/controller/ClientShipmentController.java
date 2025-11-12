package com.shalom.shalom_backend_app.shipment.infraestructure.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shalom.shalom_backend_app.shared.api.ApiResponse;
import com.shalom.shalom_backend_app.shipment.application.GetClientShipmentsService;
import com.shalom.shalom_backend_app.shipment.domain.model.Shipment;
import com.shalom.shalom_backend_app.shipment.infraestructure.mapper.ShipmentMapper;
import com.shalom.shalom_backend_app.shipment.infraestructure.web.dto.response.ClientShipmentResponseDTO;
import com.shalom.shalom_backend_app.user.infraestructure.security.CustomUserDetails;

@RestController
@RequestMapping("/api/client/shipments")
public class ClientShipmentController {

    private final GetClientShipmentsService clientShipmentsService;

    public ClientShipmentController(GetClientShipmentsService clientShipmentsService) {
        this.clientShipmentsService = clientShipmentsService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClientShipmentResponseDTO>>> getMyShipments(Authentication auth) {
        try {
            if (!(auth.getPrincipal() instanceof CustomUserDetails userDetails)) {
                return ResponseEntity.badRequest()
                        .body(ApiResponse.error("Error: usuario no autenticado correctamente."));
            }

            Long clientId = userDetails.getId();

            List<Shipment> shipments = clientShipmentsService.getShipmentsByClientId(clientId);
            List<ClientShipmentResponseDTO> dto = shipments.stream()
                    .map(ShipmentMapper::toClientResponseDTO)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(ApiResponse.success("Lista de envíos del cliente.", dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error interno del servidor."));
        }
    }

    // UC09: Consultar estado de envio

    @GetMapping("/{code}")
    public ResponseEntity<ApiResponse<ClientShipmentResponseDTO>> getMyShipmentByCode(
            @PathVariable String code,
            Authentication auth) {
        try {
            if (!(auth.getPrincipal() instanceof CustomUserDetails userDetails)) {
                return ResponseEntity.badRequest()
                        .body(ApiResponse.error("Error: usuario no autenticado correctamente."));
            }

            Long clientId = userDetails.getId();

            Shipment shipment = clientShipmentsService.getShipmentByCodeAndClient(clientId, code);
            ClientShipmentResponseDTO dto = ShipmentMapper.toClientResponseDTO(shipment);

            return ResponseEntity.ok(ApiResponse.success("Envío encontrado.", dto));

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error interno del servidor."));
        }
    }
}
