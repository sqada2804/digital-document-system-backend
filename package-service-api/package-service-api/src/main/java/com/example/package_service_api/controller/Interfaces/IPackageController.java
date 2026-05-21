package com.example.package_service_api.controller.Interfaces;

import com.example.package_service_api.common.constants.ApiPathConstants;
import com.example.package_service_api.common.dtos.CreatePackageRequestDTO;
import com.example.package_service_api.common.dtos.UpdatePackageRequestDTO;
import com.example.package_service_api.common.entities.PackageModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.PACKAGE_ROUTE)
public interface IPackageController {
    @PostMapping
    ResponseEntity<PackageModel> createPackage(@RequestBody CreatePackageRequestDTO packageDTO, @RequestHeader("X-User-Id")String userId);

    @GetMapping(value = "/{trackingNumber}")
    ResponseEntity<PackageModel> getPackage(@RequestHeader("X-User-Id") String userId, @PathVariable Long trackingNumber);

    @GetMapping()
    ResponseEntity<List<PackageModel>>getAllPackages(@RequestHeader("X-User-Id") String userId);

    @PutMapping(value = "/{trackingNumber}")
    ResponseEntity<Void> updatePackage(@RequestBody UpdatePackageRequestDTO packageDTO, @RequestHeader("X-User-Id") String userId, @PathVariable Long trackingNumber);

    @DeleteMapping(value = "/{trackingNumber}")
    ResponseEntity<Void> deletePackage(@RequestHeader("X-User-Id") String userId, @PathVariable Long trackingNumber);

}
