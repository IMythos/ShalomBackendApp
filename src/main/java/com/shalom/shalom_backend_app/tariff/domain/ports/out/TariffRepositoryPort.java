package com.shalom.shalom_backend_app.tariff.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.shalom.shalom_backend_app.tariff.domain.model.Tariff;

public interface TariffRepositoryPort {
    Tariff save(Tariff tariff);
    Optional<Tariff> findById(Long id);
    List<Tariff> findAll();
    void deleteById(Long id);
}
