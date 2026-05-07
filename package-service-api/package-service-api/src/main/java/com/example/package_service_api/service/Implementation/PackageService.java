package com.example.package_service_api.service.Implementation;

import com.example.package_service_api.common.dtos.CreatePackageRequestDTO;
import com.example.package_service_api.common.dtos.UpdatePackageRequestDTO;
import com.example.package_service_api.common.entities.PackageModel;
import com.example.package_service_api.common.exceptions.NotFoundException;
import com.example.package_service_api.common.exceptions.UnauthorizedException;
import com.example.package_service_api.repository.IPackageRepository;
import com.example.package_service_api.service.Interfaces.IPackageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
    public List<PackageModel> getAllPackages(String userId) {
        return packageRepository.findAllByUserId(userId);
    }

    @Override
    public void UpdatePackage(UpdatePackageRequestDTO letterDTO, String userId, Long trackingNumber) {
        packageRepository.findPackageByUserIdAndTrackingNumber(userId, trackingNumber)
                .map(packageExists -> updatePackageFields(packageExists, letterDTO))
                .map(packageRepository::save)
                .orElseThrow(() -> new NotFoundException("Package wasn't found to update"));
    }

    private PackageModel updatePackageFields(PackageModel packageExists, UpdatePackageRequestDTO packageDTO) {
        packageExists.setAddress(packageDTO.getAddress());
        packageExists.setContent(packageDTO.getContent());
        packageExists.setWeight(packageDTO.getWeight());
        packageExists.setReceiverEmail(packageDTO.getReceiverEmail());
        return packageExists;
    }

    @Override
    public void deletePackage(String userId, Long trackingNumber) {
        packageRepository.findPackageByUserIdAndTrackingNumber(userId, trackingNumber)
                .ifPresentOrElse(packageRepository::delete, () -> {
                    throw new NotFoundException("Package wasn't found to delete");
                });
    }
}
