package com.shalom.shalom_backend_app.shipment.infraestructure.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.shalom.shalom_backend_app.shipment.domain.model.Package;
import com.shalom.shalom_backend_app.shipment.domain.ports.out.PackageRepositoryPort;
import com.shalom.shalom_backend_app.shipment.infraestructure.mapper.PackageMapper;
import com.shalom.shalom_backend_app.shipment.infraestructure.persistence.entity.PackageEntity;
import com.shalom.shalom_backend_app.shipment.infraestructure.persistence.repository.PackageRepository;

@Component
public class PackageRepositoryAdapter implements PackageRepositoryPort {
    
    private final PackageRepository packageRepository;

    public PackageRepositoryAdapter(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    @Override
    public void deleteById(Long id) {
        packageRepository.deleteById(id);
    }

    @Override
    public List<Package> findAll() {
        return packageRepository.findAll().stream().map(PackageMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Package> findById(Long id) {
        return packageRepository.findById(id).map(PackageMapper::toDomain);
    }

    @Override
    public Package save(Package pkg) {
        PackageEntity entity = PackageMapper.toEntity(pkg);
        entity = packageRepository.save(entity);

        return PackageMapper.toDomain(entity);
    }
}
