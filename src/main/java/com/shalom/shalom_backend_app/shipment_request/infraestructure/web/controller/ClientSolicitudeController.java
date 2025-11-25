package com.shalom.shalom_backend_app.shipment_request.infraestructure.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shalom.shalom_backend_app.shared.api.ApiResponse;
import com.shalom.shalom_backend_app.shipment_request.application.LocalStorageService;
import com.shalom.shalom_backend_app.shipment_request.application.SolicitudeService;
import com.shalom.shalom_backend_app.shipment_request.domain.model.Solicitude;
import com.shalom.shalom_backend_app.shipment_request.infraestructure.mapper.SolicitudeMapper;
import com.shalom.shalom_backend_app.shipment_request.infraestructure.web.dto.request.SolicitudeRequestDTO;
import com.shalom.shalom_backend_app.shipment_request.infraestructure.web.dto.response.SolicitudeResponseDTO;
import com.shalom.shalom_backend_app.user.infraestructure.security.CustomUserDetails;

@RestController
@RequestMapping("/api/client/solicitudes")
public class ClientSolicitudeController {
    private final SolicitudeService solicitudeService;
    private final LocalStorageService localStorageService;

    public ClientSolicitudeController(SolicitudeService solicitudeService, LocalStorageService storageService) {
        this.solicitudeService = solicitudeService;
        this.localStorageService = storageService;
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<SolicitudeResponseDTO>> createSolicitude(
        @RequestPart("data") String rawJson, 
        @RequestPart("image") MultipartFile image
    ) {
        Long authenticatedClientId = null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            SolicitudeRequestDTO requestDTO = mapper.readValue(rawJson, SolicitudeRequestDTO.class);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null || !authentication.isAuthenticated()) {
                throw new IllegalStateException("Usuario no autenticado.");
            }

            Object principal = authentication.getPrincipal();

            if (principal instanceof CustomUserDetails) {
                CustomUserDetails userDetails = (CustomUserDetails) principal;
                authenticatedClientId = userDetails.getId();
            } else {
                throw new IllegalStateException("No se puede obtener el ID de usuario del contexto de seguridad.");
            }

            String imageUrl = localStorageService.storeImage(image);
            Solicitude domain = SolicitudeMapper.toDomain(requestDTO);

            domain.setClientId(authenticatedClientId);
            domain.setPackageImageUrl(imageUrl);

            Solicitude created = solicitudeService.createSolicitude(domain);

            return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Solicitud registrada correctamente. Pendiente de aprobacion.", SolicitudeMapper.toResponseDTO(created)));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.error("Error de seguridad: " + e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error interno del servidor al registrar la solicitud."));
        }
    }
}
