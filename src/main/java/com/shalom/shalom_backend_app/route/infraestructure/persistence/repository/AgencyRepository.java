package com.shalom.shalom_backend_app.route.infraestructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shalom.shalom_backend_app.route.infraestructure.persistence.entity.AgencyEntity;

@Repository
public interface AgencyRepository extends JpaRepository<AgencyEntity, Long> {
    
}
