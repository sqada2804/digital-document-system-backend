package com.example.eligibility_microservice.common;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PackageCreatedEvent {
    private UUID trackingNumber;

    private String address;

    private String content;

    private Double weight;

    private String receiverEmail;

    private UUID userId;
}
