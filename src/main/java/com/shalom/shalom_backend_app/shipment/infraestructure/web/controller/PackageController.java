package com.shalom.shalom_backend_app.shipment.infraestructure.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shalom.shalom_backend_app.shared.api.ApiResponse;
import com.shalom.shalom_backend_app.shipment.application.PackageService;
import com.shalom.shalom_backend_app.shipment.domain.model.Package;
import com.shalom.shalom_backend_app.shipment.infraestructure.mapper.PackageMapper;
import com.shalom.shalom_backend_app.shipment.infraestructure.web.dto.request.PackageRequestDTO;
import com.shalom.shalom_backend_app.shipment.infraestructure.web.dto.response.PackageResponseDTO;

@RestController
@RequestMapping("/api/packages")
public class PackageController {
    
    private final PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    // CUS06: Gestionar paquetes
    
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<PackageResponseDTO>> createPackage(@RequestBody PackageRequestDTO dto) {
        try {
            Package domain = PackageMapper.toDomain(dto);
            Package created = packageService.createPackage(domain);

            return ResponseEntity.ok(ApiResponse.success("Paquete agregado correctamente.", PackageMapper.toResponseDTO(created)));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error al crear nuevo paquete."));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PackageResponseDTO>>> list() {
        try {
            List<PackageResponseDTO> list = packageService.listPackages().stream().map(pkg -> 
                new PackageResponseDTO(pkg.getId(), pkg.getWeight(), pkg.getHeight(), pkg.getLength(), pkg.getDescription())
            ).collect(Collectors.toList());

            return ResponseEntity.ok(ApiResponse.success("Listado de paquetes", list));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error al obtener el listado de paquetes."));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<PackageResponseDTO>> getPackageById(@RequestParam Long id) {
        try {
            Package found = packageService.findPackageById(id).orElseThrow(() -> new RuntimeException("Paquete no encontrado con id: " + id));
            PackageResponseDTO dto = PackageMapper.toResponseDTO(found);
            
            return ResponseEntity.ok(ApiResponse.success("Paquete encontrado", dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error al obtener el paquete."));
        }
    }
}
