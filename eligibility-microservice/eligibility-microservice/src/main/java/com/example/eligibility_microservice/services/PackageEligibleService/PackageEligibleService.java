package com.example.eligibility_microservice.services.PackageEligibleService;

import com.example.eligibility_microservice.common.PackageEvents.PackageCreatedEvent;
import com.example.eligibility_microservice.common.PackageEvents.PackageEligibleEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PackageEligibleService implements IPackageEligibleService{

    @Override
    public Mono<PackageEligibleEvent> eligiblePackage(PackageCreatedEvent packageCreatedEvent) {
        return Mono.just(packageCreatedEvent)
                .flatMap(this::checkIsEligible)
                .map(givenCreated -> PackageEligibleEvent.builder()
                        .trackingNumber(givenCreated.getTrackingNumber())
                        .isEligible(true)
                        .build());
    }

    private Mono<PackageCreatedEvent> checkIsEligible(PackageCreatedEvent packageCreatedEvent) {
        return Mono.just(packageCreatedEvent)
                .filter(given -> given.getAddress() != null && !given.getAddress().isBlank())
                .filter(given -> given.getContent() != null && !given.getContent().isBlank())
                .filter(given -> given.getReceiverEmail() != null && !given.getReceiverEmail().isBlank())
                .filter(given -> given.getWeight() > -1 )
                .filter(given -> given.getUserId() != null)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Package does not meet eligibility requirements")));
    }
}
