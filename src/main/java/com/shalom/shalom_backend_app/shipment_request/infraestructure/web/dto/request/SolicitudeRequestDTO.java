package com.shalom.shalom_backend_app.shipment_request.infraestructure.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SolicitudeRequestDTO {

    @NotBlank(message = "El DNI del destinatario es obligatorio.")
    private String recipientDni;

    @NotBlank(message = "El nombre del destinatario es obligatorio.")
    private String recipientName;

    private String recipientCity;

    @NotBlank(message = "El destino es obligatorio.")
    private String destinationCity;

    @NotBlank(message = "La descripcion del paquete es obligatoria.")
    private String description;
}
