package com.shalom.shalom_backend_app.invoice.infraestructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shalom.shalom_backend_app.invoice.infraestructure.persistence.entity.InvoiceEntity;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {
    boolean existsByShipmentId(Long shipmentId);
}
