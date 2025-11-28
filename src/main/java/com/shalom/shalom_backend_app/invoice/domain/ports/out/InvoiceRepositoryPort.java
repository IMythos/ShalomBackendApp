package com.shalom.shalom_backend_app.invoice.domain.ports.out;

import java.util.Optional;

import com.shalom.shalom_backend_app.invoice.domain.model.Invoice;

public interface InvoiceRepositoryPort {
    Invoice save(Invoice invoice);
    Optional<Invoice> findById(Long id);
    boolean existsByShipmentId(Long shipmentId);
}
