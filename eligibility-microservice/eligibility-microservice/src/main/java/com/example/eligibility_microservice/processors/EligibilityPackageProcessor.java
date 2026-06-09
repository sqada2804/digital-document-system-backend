package com.example.eligibility_microservice.processors;

import com.example.eligibility_microservice.common.PackageEvents.PackageCreatedEvent;
import com.example.eligibility_microservice.common.PackageEvents.PackageEligibleEvent;
import com.example.eligibility_microservice.services.PackageEligibleService.IPackageEligibleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class EligibilityPackageProcessor {

    private final IPackageEligibleService packageEligibleService;

    public EligibilityPackageProcessor(IPackageEligibleService packageEligibleService) {
        this.packageEligibleService = packageEligibleService;
    }

    public Flux<PackageEligibleEvent> process(Flux<PackageCreatedEvent> packageCreatedEventFlux){
        return packageCreatedEventFlux.doOnNext(given -> log.info("Entry event: {}", given))
                .flatMap(packageEligibleService::eligiblePackage)
                .onErrorContinue(this::handleError);
    }

    private void handleError(Throwable throwable, Object o){
        log.error("Error processing event: {}", o, throwable);
    }
}
