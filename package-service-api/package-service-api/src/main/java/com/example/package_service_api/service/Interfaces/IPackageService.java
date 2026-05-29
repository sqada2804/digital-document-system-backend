package com.example.package_service_api.service.Interfaces;

import com.example.package_service_api.common.dtos.CreatePackageRequestDTO;
import com.example.package_service_api.common.dtos.UpdatePackageRequestDTO;
import com.example.package_service_api.common.entities.PackageModel;

import java.util.List;
import java.util.UUID;

public interface IPackageService {
    PackageModel createPackage(CreatePackageRequestDTO letterDTO, UUID userId);
    PackageModel getPackageById(UUID userId, UUID trackingNumber);
    List<PackageModel> getAllPackages(UUID userId);
    void UpdatePackage(UpdatePackageRequestDTO letterDTO, UUID userId, UUID trackingNumber);
    void deletePackage(UUID userId, UUID trackingNumber);
}
