package com.example.eligibility_microservice.processors;

import com.example.eligibility_microservice.common.LetterEvents.LetterCreatedEvent;
import com.example.eligibility_microservice.common.LetterEvents.LetterEligibleEvent;
import com.example.eligibility_microservice.services.LetterEligibleService.ILetterEligibleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class EligibilityLetterProcessor {

    private final ILetterEligibleService letterEligibleService;

    public EligibilityLetterProcessor(ILetterEligibleService letterEligibleService) {
        this.letterEligibleService = letterEligibleService;
    }

    public Flux<LetterEligibleEvent> process(Flux<LetterCreatedEvent> letterCreatedEventFlux){
        return letterCreatedEventFlux.doOnNext(given -> log.info("Entry event: {}", given))
                .flatMap(letterEligibleService::eligibleLetter)
                .onErrorContinue(this::handleError);
    }

    private void handleError(Throwable throwable, Object o) {
        log.error("Error processing event: {}", o, throwable);
    }
}
