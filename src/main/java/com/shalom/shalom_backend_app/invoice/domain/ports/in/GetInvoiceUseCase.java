package com.shalom.shalom_backend_app.invoice.domain.ports.in;

import java.util.Optional;

import com.shalom.shalom_backend_app.invoice.domain.model.Invoice;

public interface GetInvoiceUseCase {
    Optional<Invoice> getById(Long id);
}
