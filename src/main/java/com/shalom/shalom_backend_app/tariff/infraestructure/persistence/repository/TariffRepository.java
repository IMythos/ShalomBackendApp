package com.shalom.shalom_backend_app.tariff.infraestructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shalom.shalom_backend_app.tariff.infraestructure.persistence.entity.TariffEntity;

@Repository
public interface TariffRepository extends JpaRepository<TariffEntity, Long>{
    
}
