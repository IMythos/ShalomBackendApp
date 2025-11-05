package com.shalom.shalom_backend_app.shipment.domain.ports.in;

import java.util.List;
import java.util.Optional;

import com.shalom.shalom_backend_app.shipment.domain.model.Package;

public interface ManagePackageUseCase {
    Package createPackage(Package pkg);
    Package updatePackage(Long id, Package pkg);
    void deletePackage(Long id);
    List<Package> listPackages();
    Optional<Package> findPackageById(Long id);    
}
