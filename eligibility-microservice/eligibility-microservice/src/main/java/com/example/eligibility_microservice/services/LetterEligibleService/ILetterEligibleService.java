package com.example.eligibility_microservice.services.LetterEligibleService;

import com.example.eligibility_microservice.common.LetterEvents.LetterCreatedEvent;
import com.example.eligibility_microservice.common.LetterEvents.LetterEligibleEvent;
import reactor.core.publisher.Mono;

public interface ILetterEligibleService {
    Mono<LetterEligibleEvent> eligibleLetter(LetterCreatedEvent letterCreatedEvent);
}
