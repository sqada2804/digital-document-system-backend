package com.example.notification_microservice.services.Interface;

import reactor.core.publisher.Mono;

public interface IMailgunService {
    Mono<Void> sendEmail(String receiver, String subject, String html);
}
