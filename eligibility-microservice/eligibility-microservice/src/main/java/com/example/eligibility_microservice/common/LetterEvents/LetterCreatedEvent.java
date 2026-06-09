package com.example.eligibility_microservice.common.LetterEvents;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LetterCreatedEvent {

    private UUID trackingNumber;

    private String address;

    private String subject;

    private String body;

    private String receiverEmail;

    private UUID userId;
}
