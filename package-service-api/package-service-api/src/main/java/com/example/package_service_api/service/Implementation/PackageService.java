package com.example.package_service_api.service.Implementation;

import com.example.package_service_api.common.dtos.CreatePackageRequestDTO;
import com.example.package_service_api.common.dtos.UpdatePackageRequestDTO;
import com.example.package_service_api.common.entities.PackageModel;
import com.example.package_service_api.common.exceptions.UnauthorizedException;
import com.example.package_service_api.repository.IPackageRepository;
import com.example.package_service_api.service.Interfaces.IPackageService;

import java.util.Optional;

public class PackageService implements IPackageService {

    private final IPackageRepository packageRepository;

    public PackageService(IPackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    @Override
    public PackageModel createPackage(CreatePackageRequestDTO packageDTO, String userId) {
        return Optional.of(packageDTO)
                .map(packages -> mapToEntity(packages, userId))
                .map(packageRepository::save)
                .orElseThrow(() -> new UnauthorizedException("Unauthorized to create a package"));

    }

    private PackageModel mapToEntity(CreatePackageRequestDTO packagesDTO, String userId) {
        return PackageModel.builder().address(packagesDTO.getAddress())
                .content(packagesDTO.getContent())
                .weight(packagesDTO.getWeight())
                .receiverEmail(packagesDTO.getReceiverEmail())
                .userId(userId)
                .build();
    }

    @Override
    public PackageModel getPackageById(String userId, Long trackingNumber) {
        return Optional.of(userId)
                .flatMap(userId1 -> packageRepository.findPackageByUserIdAndTrackingNumber(userId1, trackingNumber))
                .orElseThrow(() -> new RuntimeException("Error finding package by id"));
    }

    @Override
    public PackageModel getAllPackages(String userId) {
        return null;
    }

    @Override
    public void UpdatePackage(UpdatePackageRequestDTO packageDTO, String userId, Long trackingNumber) {

    }

    @Override
    public void deletePackage(String userId, Long trackingNumber) {

    }
}
