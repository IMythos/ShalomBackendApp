package com.shalom.shalom_backend_app.shipment.infraestructure.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shalom.shalom_backend_app.shared.api.ApiResponse;
import com.shalom.shalom_backend_app.shipment.application.ShipmentService;
import com.shalom.shalom_backend_app.shipment.domain.model.Shipment;
import com.shalom.shalom_backend_app.shipment.infraestructure.mapper.ShipmentMapper;
import com.shalom.shalom_backend_app.shipment.infraestructure.web.dto.request.ShipmentRequestDTO;
import com.shalom.shalom_backend_app.shipment.infraestructure.web.dto.response.ShipmentResponseDTO;

@RestController
@RequestMapping("/api/shipments")
@CrossOrigin(origins = "*")
public class ShipmentController {
    
    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ShipmentResponseDTO>> createShipment(@RequestBody ShipmentRequestDTO dto) {
        try {
            Shipment domain = ShipmentMapper.toDomain(dto);
            Shipment created = shipmentService.createShipment(domain);

            return ResponseEntity.ok(ApiResponse.success("Envio registrado correctamente.", ShipmentMapper.toResponseDTO(created)));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error interno del servidor."));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<ShipmentResponseDTO>> updateShipment(@PathVariable Long id, @RequestBody ShipmentRequestDTO dto) {
        try {
            Shipment domain = ShipmentMapper.toDomain(dto);
            Shipment updated = shipmentService.updateShipment(id, domain);

            return ResponseEntity.ok(ApiResponse.success("Envio actualizado correctamente.", ShipmentMapper.toResponseDTO(updated)));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error interno del servidor."));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ShipmentResponseDTO>> getShipmentById(@PathVariable Long id) {
        return shipmentService.findShipmentById(id)
                .map(shipment -> ResponseEntity.ok(ApiResponse.success("Envio encontrado.", ShipmentMapper.toResponseDTO(shipment))))
                .orElse(ResponseEntity.status(404).body(ApiResponse.error("No se encontro el envio con ID: " + id))); 
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<ApiResponse<ShipmentResponseDTO>> getShipmentByCode(@PathVariable String code) {
        return shipmentService.findShipmentByCode(code)
                .map(shipment -> ResponseEntity.ok(ApiResponse.success("Envío encontrado.", ShipmentMapper.toResponseDTO(shipment))))
                .orElse(ResponseEntity.status(404).body(ApiResponse.error("No se encontró el envío con código: " + code)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ShipmentResponseDTO>>> list() {
        try {
            List<ShipmentResponseDTO> list = shipmentService.listShipments().stream().map(shipment -> 
                ShipmentMapper.toResponseDTO(shipment)
            ).collect(Collectors.toList());

            return ResponseEntity.ok(ApiResponse.success("Listado de envíos", list));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error al obtener el envio."));
        }
    }
}
