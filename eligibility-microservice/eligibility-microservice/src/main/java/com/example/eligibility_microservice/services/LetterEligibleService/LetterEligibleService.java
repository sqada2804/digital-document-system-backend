package com.example.eligibility_microservice.services.LetterEligibleService;

import com.example.eligibility_microservice.common.LetterEvents.LetterCreatedEvent;
import com.example.eligibility_microservice.common.LetterEvents.LetterEligibleEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class LetterEligibleService implements ILetterEligibleService{

    @Override
    public Mono<LetterEligibleEvent> eligibleLetter(LetterCreatedEvent letterCreatedEvent) {
        return Mono.just(letterCreatedEvent)
                .flatMap(this::checkIsEligible)
                .map(givenCreated -> LetterEligibleEvent.builder()
                        .trackingNumber(givenCreated.getTrackingNumber())
                        .IsEligible(true)
                        .build());
    }

    private Mono<LetterCreatedEvent> checkIsEligible(LetterCreatedEvent letterCreatedEvent) {
        return Mono.just(letterCreatedEvent)
                .filter(given -> given.getAddress() != null && given.getAddress().isBlank())
                .filter(given -> given.getReceiverEmail() != null && given.getReceiverEmail().isBlank())
                .filter(given -> given.getBody() != null && given.getBody().isBlank())
                .filter(given -> given.getSubject() != null && given.getSubject().isBlank())
                .filter(given -> given.getTrackingNumber() != null)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Letter does not meet eligibility requirements")));
    }
}
