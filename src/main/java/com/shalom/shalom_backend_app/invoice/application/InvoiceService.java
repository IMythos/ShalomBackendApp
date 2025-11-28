package com.shalom.shalom_backend_app.invoice.application;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shalom.shalom_backend_app.invoice.domain.model.Invoice;
import com.shalom.shalom_backend_app.invoice.domain.ports.in.EmitInvoiceUseCase;
import com.shalom.shalom_backend_app.invoice.domain.ports.in.GetInvoiceUseCase;
import com.shalom.shalom_backend_app.invoice.domain.ports.out.InvoiceRepositoryPort;
import com.shalom.shalom_backend_app.shipment.domain.model.Shipment;
import com.shalom.shalom_backend_app.shipment.domain.ports.out.ShipmentRepositoryPort;
import com.shalom.shalom_backend_app.user.domain.model.User;
import com.shalom.shalom_backend_app.user.domain.ports.out.UserRepositoryPort;

@Service
public class InvoiceService implements EmitInvoiceUseCase, GetInvoiceUseCase {

    private final InvoiceRepositoryPort invoiceRepositoryPort;
    private final ShipmentRepositoryPort shipmentRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;

    public InvoiceService(InvoiceRepositoryPort invoiceRepositoryPort, ShipmentRepositoryPort shipmentRepositoryPort, UserRepositoryPort userRepositoryPort) {
        this.invoiceRepositoryPort = invoiceRepositoryPort;
        this.shipmentRepositoryPort = shipmentRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public Invoice emit(Invoice invoice) {
        Long shipmentId = invoice.getShipment().getId();
        Long issuedById = invoice.getIssuedBy().getId();

        Optional<Shipment> shipmentOpt = shipmentRepositoryPort.findById(shipmentId);
        if (shipmentOpt.isEmpty()) { 
             throw new IllegalArgumentException("El envío con ID " + shipmentId + " no existe.");
        }

        Optional<User> userOpt = userRepositoryPort.findById(issuedById);
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("El empleado con ID " + issuedById + " no existe.");
        }

        invoice.setShipment(shipmentOpt.get());
        invoice.setIssuedBy(userOpt.get());

        if (invoiceRepositoryPort.existsByShipmentId(shipmentId)) {
            throw new IllegalStateException("Ya existe una factura para el envío con ID: " + shipmentId);
        }

        if (invoice.getTotalAmount() == null || invoice.getTotalAmount() <= 0) {
            throw new IllegalArgumentException("El monto total debe ser positivo.");
        }

        return invoiceRepositoryPort.save(invoice);
    }

    @Override
    public Optional<Invoice> getById(Long id) {
        return invoiceRepositoryPort.findById(id);
    }

}
