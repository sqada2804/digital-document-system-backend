package com.example.eligibility_microservice.configuration;

import com.example.eligibility_microservice.common.PackageCreatedEvent;
import com.example.eligibility_microservice.common.PackageEligibleEvent;
import com.example.eligibility_microservice.processors.EligibilityPackageProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Configuration
public class StreamConfig {
    @Bean
    public Function<Flux<PackageCreatedEvent>, Flux<PackageEligibleEvent>> packageCreatedBinding(final EligibilityPackageProcessor processor){
        return processor::process;
    }
}
