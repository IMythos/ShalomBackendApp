package com.shalom.shalom_backend_app.shipment.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.shalom.shalom_backend_app.shipment.domain.model.Package;

public interface PackageRepositoryPort {
    Package save(Package pkg);
    Optional<Package> findById(Long id);
    List<Package> findAll();
    void deleteById(Long id);
}
