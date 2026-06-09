package com.example.eligibility_microservice.services.PackageEligibleService;

import com.example.eligibility_microservice.common.PackageEvents.PackageCreatedEvent;
import com.example.eligibility_microservice.common.PackageEvents.PackageEligibleEvent;
import reactor.core.publisher.Mono;

public interface IPackageEligibleService {
    Mono<PackageEligibleEvent> eligiblePackage(PackageCreatedEvent packageCreatedEvent);
}
