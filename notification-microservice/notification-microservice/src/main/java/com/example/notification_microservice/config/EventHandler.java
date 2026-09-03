package com.example.notification_microservice.config;

import com.example.eligibility_microservice.common.LetterEvents.LetterEligibleEvent;
import com.example.eligibility_microservice.common.PackageEvents.PackageEligibleEvent;
import com.example.notification_microservice.services.Interface.INotificationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class EventHandler {

    private final INotificationService notificationService;

    public EventHandler(INotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Bean
    public Consumer<LetterEligibleEvent> letterEligibleBinding(){
        return notificationService::processLetter;
    }

    @Bean
    public Consumer<PackageEligibleEvent> packageEligibleBinding(){
        return notificationService::processPackage;
    }
}
