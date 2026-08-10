package com.example.eligibility_microservice.configuration;

import com.example.eligibility_microservice.common.LetterEvents.LetterCreatedEvent;
import com.example.eligibility_microservice.common.LetterEvents.LetterEligibleEvent;
import com.example.eligibility_microservice.common.PackageEvents.PackageCreatedEvent;
import com.example.eligibility_microservice.common.PackageEvents.PackageEligibleEvent;
import com.example.eligibility_microservice.processors.EligibilityLetterProcessor;
import com.example.eligibility_microservice.processors.EligibilityPackageProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Configuration
public class StreamConfig {

    @Bean
    public Function<Flux<PackageCreatedEvent>, Flux<PackageEligibleEvent>> packageCreatedBinding(
            final EligibilityPackageProcessor processor) {
        return processor::process;
    }

    @Bean
    public Function<Flux<LetterCreatedEvent>, Flux<LetterEligibleEvent>> letterCreatedBinding(
            final EligibilityLetterProcessor processor) {
        return processor::process;
    }
}
