package com.shalom.shalom_backend_app.invoice.infraestructure.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shalom.shalom_backend_app.invoice.application.InvoiceService;
import com.shalom.shalom_backend_app.invoice.domain.model.Invoice;
import com.shalom.shalom_backend_app.invoice.infraestructure.mapper.InvoiceMapper;
import com.shalom.shalom_backend_app.invoice.infraestructure.web.dto.request.InvoiceRequestDTO;
import com.shalom.shalom_backend_app.invoice.infraestructure.web.dto.response.InvoiceResponseDTO;
import com.shalom.shalom_backend_app.shared.api.ApiResponse;
import com.shalom.shalom_backend_app.shipment.domain.model.Shipment;
import com.shalom.shalom_backend_app.user.domain.model.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<InvoiceResponseDTO>> createInvoice(@Valid @RequestBody InvoiceRequestDTO dto) {
        try {
            Invoice invoiceToCreate = new Invoice();
            invoiceToCreate.setNumber(dto.getNumber());
            invoiceToCreate.setDate(dto.getDate());
            invoiceToCreate.setTotalAmount(dto.getTotalAmount());

            Shipment shipment = new Shipment();
            shipment.setId(dto.getShipmentId());
            invoiceToCreate.setShipment(shipment);

            User issuedBy = new User();
            issuedBy.setId(dto.getIssuedByUserId());
            invoiceToCreate.setIssuedBy(issuedBy);

            Invoice createdInvoice = invoiceService.emit(invoiceToCreate);

            return ResponseEntity.ok(
                    ApiResponse.success(
                            "Factura emitida correctamente.",
                            InvoiceMapper.toResponseDTO(createdInvoice)));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("Error interno del servidor al emitir la factura."));
        }
    }

    /**
     * Obtiene una factura por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InvoiceResponseDTO>> getInvoiceById(@PathVariable Long id) {
        return invoiceService.getById(id)
                .map(invoice -> ResponseEntity.ok(
                        ApiResponse.success("Factura encontrada.", InvoiceMapper.toResponseDTO(invoice))))
                .orElse(
                        ResponseEntity.status(404).body(ApiResponse.error("No se encontró la factura con ID: " + id)));
    }
}