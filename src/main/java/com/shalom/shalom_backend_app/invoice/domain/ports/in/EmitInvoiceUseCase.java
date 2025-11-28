package com.shalom.shalom_backend_app.invoice.domain.ports.in;

import com.shalom.shalom_backend_app.invoice.domain.model.Invoice;

public interface EmitInvoiceUseCase {
    Invoice emit(Invoice invoice);
}
