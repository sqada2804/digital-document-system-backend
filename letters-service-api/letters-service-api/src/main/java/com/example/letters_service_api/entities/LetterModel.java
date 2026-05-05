package com.example.letters_service_api.entities;

import com.example.common_library.entity.UserModel;
import jakarta.persistence.*;
import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long trackingNumber;

    private String address;

    private String subject;

    @Column(columnDefinition = "TEXT")
    private String body;

    private String receiverEmail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserModel userId;
}
