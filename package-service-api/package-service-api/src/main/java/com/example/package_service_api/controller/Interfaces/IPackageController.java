package com.example.package_service_api.controller.Interfaces;

import com.example.package_service_api.common.constants.ApiPathConstants;
import com.example.package_service_api.common.dtos.CreatePackageRequestDTO;
import com.example.package_service_api.common.dtos.UpdatePackageRequestDTO;
import com.example.package_service_api.common.entities.PackageModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.PACKAGE_ROUTE)
public interface IPackageController {
    @PostMapping
    ResponseEntity<PackageModel> createPackage(@RequestBody CreatePackageRequestDTO packageDTO, @AuthenticationPrincipal Jwt jwt);

    @GetMapping(value = "/{trackingNumber}")
    ResponseEntity<PackageModel> getPackage(@AuthenticationPrincipal Jwt jwt, @PathVariable Long trackingNumber);

    @GetMapping()
    ResponseEntity<List<PackageModel>>getAllPackages(@AuthenticationPrincipal Jwt jwt);

    @PutMapping(value = "/{trackingNumber}")
    ResponseEntity<Void> updatePackage(@RequestBody UpdatePackageRequestDTO packageDTO, @AuthenticationPrincipal Jwt jwt, @PathVariable Long trackingNumber);

    @DeleteMapping(value = "/{trackingNumber}")
    ResponseEntity<Void> deletePackage(@AuthenticationPrincipal Jwt jwt, @PathVariable Long trackingNumber);

}
