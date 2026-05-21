package com.example.package_service_api.controller.Implementation;

import com.example.package_service_api.common.dtos.CreatePackageRequestDTO;
import com.example.package_service_api.common.dtos.UpdatePackageRequestDTO;
import com.example.package_service_api.common.entities.PackageModel;
import com.example.package_service_api.controller.Interfaces.IPackageController;
import com.example.package_service_api.service.Implementation.PackageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PackageController implements IPackageController {

    private final PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @Override
    public ResponseEntity<PackageModel> createPackage(CreatePackageRequestDTO packageDTO, String userId) {
        return ResponseEntity.ok(packageService.createPackage(packageDTO, userId));
    }

    @Override
    public ResponseEntity<PackageModel> getPackage(String userId, Long trackingNumber) {
        return ResponseEntity.ok(packageService.getPackageById(userId, trackingNumber));
    }

    @Override
    public ResponseEntity<List<PackageModel>> getAllPackages(String userId) {
        return ResponseEntity.ok(packageService.getAllPackages(userId));
    }

    @Override
    public ResponseEntity<Void> updatePackage(UpdatePackageRequestDTO packageDTO, String userId, Long trackingNumber) {
        packageService.UpdatePackage(packageDTO, userId, trackingNumber);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deletePackage(String userId, Long trackingNumber) {
        packageService.deletePackage(userId, trackingNumber);
        return ResponseEntity.noContent().build();
    }
}
