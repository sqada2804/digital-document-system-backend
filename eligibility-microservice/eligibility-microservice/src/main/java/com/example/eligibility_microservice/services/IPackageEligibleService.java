package com.example.eligibility_microservice.services;

import com.example.eligibility_microservice.common.PackageCreatedEvent;
import com.example.eligibility_microservice.common.PackageEligibleEvent;
import reactor.core.publisher.Mono;

public interface IPackageEligibleService {
    Mono<PackageEligibleEvent> eligiblePackage(PackageCreatedEvent packageCreatedEvent);
}
