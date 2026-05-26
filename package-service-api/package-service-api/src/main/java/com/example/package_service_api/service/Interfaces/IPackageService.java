package com.example.package_service_api.service.Interfaces;

import com.example.package_service_api.common.dtos.CreatePackageRequestDTO;
import com.example.package_service_api.common.dtos.UpdatePackageRequestDTO;
import com.example.package_service_api.common.entities.PackageModel;

import java.util.List;

public interface IPackageService {
    PackageModel createPackage(CreatePackageRequestDTO letterDTO, Long userId);
    PackageModel getPackageById(Long userId, Long trackingNumber);
    List<PackageModel> getAllPackages(Long userId);
    void UpdatePackage(UpdatePackageRequestDTO letterDTO, Long userId, Long trackingNumber);
    void deletePackage(Long userId, Long trackingNumber);
}
