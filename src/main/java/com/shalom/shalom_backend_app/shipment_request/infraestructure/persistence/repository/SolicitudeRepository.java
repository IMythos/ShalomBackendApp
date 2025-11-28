package com.shalom.shalom_backend_app.shipment_request.infraestructure.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.shalom.shalom_backend_app.shipment_request.infraestructure.persistence.entity.SolicitudeEntity;

@Component
public interface SolicitudeRepository extends JpaRepository<SolicitudeEntity, Long>{

    @Query("SELECT s FROM SolicitudeEntity s WHERE s.client.id = :clientId")
    List<SolicitudeEntity> findByClientId(@Param("clientId") Long id);
}
