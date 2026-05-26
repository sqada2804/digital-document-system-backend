package com.example.package_service_api.controller.Implementation;

import com.example.package_service_api.common.dtos.CreatePackageRequestDTO;
import com.example.package_service_api.common.dtos.UpdatePackageRequestDTO;
import com.example.package_service_api.common.entities.PackageModel;
import com.example.package_service_api.controller.Interfaces.IPackageController;
import com.example.package_service_api.service.Implementation.PackageService;
import com.example.package_service_api.service.Interfaces.IPackageService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PackageController implements IPackageController {

    private final IPackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @Override
    public ResponseEntity<PackageModel> createPackage(CreatePackageRequestDTO packageDTO, Jwt jwt) {
        Long userId = Long.valueOf(jwt.getSubject());
        return ResponseEntity.ok(packageService.createPackage(packageDTO, userId));
    }

    @Override
    public ResponseEntity<PackageModel> getPackage(Jwt jwt, Long trackingNumber) {
        Long userId = Long.valueOf(jwt.getSubject());
        return ResponseEntity.ok(packageService.getPackageById(userId, trackingNumber));
    }

    @Override
    public ResponseEntity<List<PackageModel>> getAllPackages(Jwt jwt) {
        Long userId = Long.valueOf(jwt.getSubject());
        return ResponseEntity.ok(packageService.getAllPackages(userId));
    }

    @Override
    public ResponseEntity<Void> updatePackage(UpdatePackageRequestDTO packageDTO, Jwt jwt, Long trackingNumber) {
        Long userId = Long.valueOf(jwt.getSubject());
        packageService.UpdatePackage(packageDTO, userId, trackingNumber);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deletePackage(Jwt jwt, Long trackingNumber) {
        Long userId = Long.valueOf(jwt.getSubject());
        packageService.deletePackage(userId, trackingNumber);
        return ResponseEntity.noContent().build();
    }
}
