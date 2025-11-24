package com.shalom.shalom_backend_app.shipment_request.infraestructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.shalom.shalom_backend_app.shipment_request.infraestructure.persistence.entity.SolicitudeEntity;

@Component
public interface SolicitudeRepository extends JpaRepository<SolicitudeEntity, Long>{
    
}
