package com.example.package_service_api.repository;

import com.example.package_service_api.common.entities.PackageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IPackageRepository extends JpaRepository<PackageModel, UUID> {
    Optional<PackageModel> findPackageByUserIdAndTrackingNumber(@Param("userId") UUID userId, @Param("trackingNumber")UUID trackingNumber);
    List<PackageModel> findAllByUserId(@Param("userId") UUID userId);
}
