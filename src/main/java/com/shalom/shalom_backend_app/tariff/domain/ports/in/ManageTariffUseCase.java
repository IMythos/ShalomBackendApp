package com.shalom.shalom_backend_app.tariff.domain.ports.in;

import java.util.List;
import java.util.Optional;

import com.shalom.shalom_backend_app.tariff.domain.model.Tariff;

public interface ManageTariffUseCase {
    Tariff createTariff(Tariff tariff);
    Tariff updateTariff(Long id, Tariff tariff);
    void deleteTariff(Long id);
    List<Tariff> listTariffs();
    Optional<Tariff> findTariffById(Long id);
}
