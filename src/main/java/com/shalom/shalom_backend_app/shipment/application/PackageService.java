package com.shalom.shalom_backend_app.shipment.application;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shalom.shalom_backend_app.shipment.domain.model.Package;
import com.shalom.shalom_backend_app.shipment.domain.ports.in.ManagePackageUseCase;
import com.shalom.shalom_backend_app.shipment.domain.ports.out.PackageRepositoryPort;

@Service
public class PackageService implements ManagePackageUseCase {

    private final PackageRepositoryPort packageRepositoryPort;

    public PackageService(PackageRepositoryPort packageRepositoryPort) {
        this.packageRepositoryPort = packageRepositoryPort;
    }

    @Override
    public Package createPackage(Package pkg) {
        return packageRepositoryPort.save(pkg);
    }

    @Override
    public void deletePackage(Long id) {
        packageRepositoryPort.deleteById(id);
    }

    @Override
    public Optional<Package> findPackageById(Long id) {
        return packageRepositoryPort.findById(id);
    }

    @Override
    public List<Package> listPackages() {
        return packageRepositoryPort.findAll();
    }

    @Override
    public Package updatePackage(Long id, Package pkg) {
        Optional<Package> existing = packageRepositoryPort.findById(id);

        if (existing.isEmpty()) {
            throw new RuntimeException("Paquete no especificado: " + id);
        }

        Package ex = existing.get();

        if (pkg.getWeight() != null) ex.setWeight(pkg.getWeight());
        if (pkg.getHeight() != null) ex.setHeight(pkg.getHeight());
        if (pkg.getLength() != null) ex.setLength(pkg.getLength());
        if (pkg.getDescription() != null) ex.setDescription(pkg.getDescription());

        return packageRepositoryPort.save(ex);
    }
    
}
