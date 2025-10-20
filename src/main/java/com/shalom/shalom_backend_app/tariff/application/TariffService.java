package com.shalom.shalom_backend_app.tariff.application;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shalom.shalom_backend_app.tariff.domain.model.Tariff;
import com.shalom.shalom_backend_app.tariff.domain.ports.in.ManageTariffUseCase;
import com.shalom.shalom_backend_app.tariff.domain.ports.out.TariffRepositoryPort;

@Service
public class TariffService implements ManageTariffUseCase {

    private final TariffRepositoryPort tariffRepositoryPort;

    public TariffService(TariffRepositoryPort tariffRepositoryPort) {
        this.tariffRepositoryPort = tariffRepositoryPort;
    }

    @Override
    public Tariff createTariff(Tariff tariff) {
        return tariffRepositoryPort.save(tariff);
    }

    @Override
    public Tariff updateTariff(Long id, Tariff tariff) {
        Optional<Tariff> existing = tariffRepositoryPort.findById(id);

        if (existing.isEmpty()) throw new RuntimeException("Tarifa no encontrada: " + id);

        Tariff ex = existing.get();

        if (tariff.getBasePrice() != null) ex.setBasePrice(tariff.getBasePrice());
        if (tariff.getPricePerKg() != null) ex.setPricePerKg(tariff.getPricePerKg());
        if (tariff.getPricePerKm() != null) ex.setPricePerKm(tariff.getPricePerKm());
        if (tariff.getEffectiveDate() != null) ex.setEffectiveDate(tariff.getEffectiveDate());
        if (tariff.getRoute() != null) ex.setRoute(tariff.getRoute());

        return tariffRepositoryPort.save(tariff);
    }

    @Override
    public void deleteTariff(Long id) {
        tariffRepositoryPort.deleteById(id);
    }

    @Override
    public List<Tariff> listTariffs() {
        return tariffRepositoryPort.findAll();
    }

    @Override
    public Optional<Tariff> findTariffById(Long id) {
        return tariffRepositoryPort.findById(id);
    }
    
}
