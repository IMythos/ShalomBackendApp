package com.shalom.shalom_backend_app.tariff.infraestructure.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.shalom.shalom_backend_app.tariff.domain.model.Tariff;
import com.shalom.shalom_backend_app.tariff.domain.ports.out.TariffRepositoryPort;
import com.shalom.shalom_backend_app.tariff.infraestructure.mapper.TariffMapper;
import com.shalom.shalom_backend_app.tariff.infraestructure.persistence.entity.TariffEntity;
import com.shalom.shalom_backend_app.tariff.infraestructure.persistence.repository.TariffRepository;

@Component
public class TariffRepositoryAdapter implements TariffRepositoryPort {
    
    private final TariffRepository tariffRepository;

    public TariffRepositoryAdapter(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    @Override
    public void deleteById(Long id) {
        tariffRepository.deleteById(id);
    }

    @Override
    public List<Tariff> findAll() {
        return tariffRepository.findAll().stream().map(TariffMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Tariff> findById(Long id) {
        return tariffRepository.findById(id).map(TariffMapper::toDomain);
    }

    @Override
    public Tariff save(Tariff tariff) {
        TariffEntity entity = TariffMapper.toEntity(tariff);
        entity = tariffRepository.save(entity);

        return TariffMapper.toDomain(entity);
    }

    
}
