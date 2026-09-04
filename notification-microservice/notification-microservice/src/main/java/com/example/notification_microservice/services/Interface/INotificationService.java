package com.example.notification_microservice.services.Interface;

import com.example.eligibility_microservice.common.LetterEvents.LetterEligibleEvent;
import com.example.eligibility_microservice.common.PackageEvents.PackageEligibleEvent;

public interface INotificationService {
    void processLetter(LetterEligibleEvent event);
    void processPackage(PackageEligibleEvent event);

}
