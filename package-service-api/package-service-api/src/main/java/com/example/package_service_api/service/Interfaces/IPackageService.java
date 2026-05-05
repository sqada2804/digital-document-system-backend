package com.example.package_service_api.service.Interfaces;

import com.example.package_service_api.common.dtos.CreatePackageRequestDTO;
import com.example.package_service_api.common.dtos.UpdatePackageRequestDTO;
import com.example.package_service_api.common.entities.PackageModel;

public interface IPackageService {
    PackageModel createPackage(CreatePackageRequestDTO packageDTO, String userId);
    PackageModel getPackageById(String userId, Long trackingNumber);
    PackageModel getAllPackages(String userId);
    void UpdatePackage(UpdatePackageRequestDTO packageDTO, String userId, Long trackingNumber);
    void deletePackage(String userId, Long trackingNumber);
}
