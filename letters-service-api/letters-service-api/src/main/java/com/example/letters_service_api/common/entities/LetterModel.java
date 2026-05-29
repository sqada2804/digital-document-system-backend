package com.example.letters_service_api.common.entities;

import com.example.common_library.entity.UserModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "letters")
public class LetterModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID trackingNumber;

    private String address;

    private String subject;

    @Column(columnDefinition = "TEXT")
    private String body;

    private String receiverEmail;

    private UUID userId;
}
