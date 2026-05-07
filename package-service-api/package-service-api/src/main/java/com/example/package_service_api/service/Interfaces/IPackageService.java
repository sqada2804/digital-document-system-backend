package com.example.package_service_api.service.Interfaces;

import com.example.package_service_api.common.dtos.CreatePackageRequestDTO;
import com.example.package_service_api.common.dtos.UpdatePackageRequestDTO;
import com.example.package_service_api.common.entities.PackageModel;

import java.util.List;

public interface IPackageService {
    PackageModel createPackage(CreatePackageRequestDTO letterDTO, String userId);
    PackageModel getPackageById(String userId, Long trackingNumber);
    List<PackageModel> getAllPackages(String userId);
    void UpdatePackage(UpdatePackageRequestDTO letterDTO, String userId, Long trackingNumber);
    void deletePackage(String userId, Long trackingNumber);
}
