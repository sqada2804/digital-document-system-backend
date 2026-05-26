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
    public PackageModel createPackage(CreatePackageRequestDTO packageDTO, Long userId) {
        return Optional.of(packageDTO)
                .map(packages -> mapToEntity(packages, userId))
                .map(packageRepository::save)
                .orElseThrow(() -> new RuntimeException("Error creating the package"));
    }

    private PackageModel mapToEntity(CreatePackageRequestDTO packagesDTO, Long userId) {
        return PackageModel.builder().address(packagesDTO.getAddress())
                .content(packagesDTO.getContent())
                .weight(packagesDTO.getWeight())
                .receiverEmail(packagesDTO.getReceiverEmail())
                .userId(userId)
                .build();
    }

    @Override
    public PackageModel getPackageById(Long userId, Long trackingNumber) {
        return packageRepository.findPackageByUserIdAndTrackingNumber(userId, trackingNumber)
                .orElseThrow(() -> new NotFoundException("Package wasn't found "));
    }

    @Override
    public List<PackageModel> getAllPackages(Long userId) {
        return packageRepository.findAllByUserId(userId);
    }

    @Override
    public void UpdatePackage(UpdatePackageRequestDTO packageDTO, Long userId, Long trackingNumber) {
        packageRepository.findPackageByUserIdAndTrackingNumber(userId, trackingNumber)
                .map(existingPackage ->
                    updatePackageFields(existingPackage, packageDTO)
                ).map(packageRepository::save)
                .orElseThrow(() -> new NotFoundException("Package wasn´t found for update"));
    }

    private PackageModel updatePackageFields(PackageModel packageExists, UpdatePackageRequestDTO packageDTO) {
        packageExists.setAddress(packageDTO.getAddress());
        packageExists.setContent(packageDTO.getContent());
        packageExists.setWeight(packageDTO.getWeight());
        packageExists.setReceiverEmail(packageDTO.getReceiverEmail());
        return packageExists;
    }

    @Override
    public void deletePackage(Long userId, Long trackingNumber) {
        packageRepository.findPackageByUserIdAndTrackingNumber(userId, trackingNumber)
                .ifPresentOrElse(packageRepository::delete, () -> {
                    throw new NotFoundException("Package wasn't found to delete");
                });
    }
}
