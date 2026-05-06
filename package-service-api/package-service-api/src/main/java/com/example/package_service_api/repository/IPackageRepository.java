package com.example.package_service_api.repository;

import com.example.package_service_api.common.entities.PackageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface IPackageRepository extends JpaRepository<PackageModel, Long> {
    Optional<PackageModel> findPackageByUserIdAndTrackingNumber(@Param("userId")String userId, @Param("{trackingNumber}")Long trackingNumber);
}
