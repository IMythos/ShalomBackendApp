package com.shalom.shalom_backend_app.shipment.infraestructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shalom.shalom_backend_app.shipment.infraestructure.persistence.entity.PackageEntity;

@Repository
public interface PackageRepository extends JpaRepository<PackageEntity, Long>{
    
}
