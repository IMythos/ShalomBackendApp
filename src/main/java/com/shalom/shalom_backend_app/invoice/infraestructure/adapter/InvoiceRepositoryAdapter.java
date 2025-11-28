package com.shalom.shalom_backend_app.invoice.infraestructure.adapter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.shalom.shalom_backend_app.invoice.domain.model.Invoice;
import com.shalom.shalom_backend_app.invoice.domain.ports.out.InvoiceRepositoryPort;
import com.shalom.shalom_backend_app.invoice.infraestructure.mapper.InvoiceMapper;
import com.shalom.shalom_backend_app.invoice.infraestructure.persistence.entity.InvoiceEntity;
import com.shalom.shalom_backend_app.invoice.infraestructure.persistence.repository.InvoiceRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InvoiceRepositoryAdapter implements InvoiceRepositoryPort {
    
    private final InvoiceRepository invoiceRepository;

    @Override
    public boolean existsByShipmentId(Long shipmentId) {
        return invoiceRepository.existsByShipmentId(shipmentId);
    }

    @Override
    public Optional<Invoice> findById(Long id) {
        return invoiceRepository.findById(id)
                .map(InvoiceMapper::toDomain); // ✔ método estático
    }

    @Override
    public Invoice save(Invoice invoice) {
        InvoiceEntity entity = InvoiceMapper.toEntity(invoice); // ✔ a entidad
        InvoiceEntity saved = invoiceRepository.save(entity);
        return InvoiceMapper.toDomain(saved); // ✔ de vuelta a dominio
    }
}
