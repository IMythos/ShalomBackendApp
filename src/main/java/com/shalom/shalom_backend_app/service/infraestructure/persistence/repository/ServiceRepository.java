package com.shalom.shalom_backend_app.service.infraestructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shalom.shalom_backend_app.service.infraestructure.persistence.entity.ServiceEntity;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long>{
    
}
