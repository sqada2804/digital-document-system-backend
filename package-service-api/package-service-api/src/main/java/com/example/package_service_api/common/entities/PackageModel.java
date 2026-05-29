package com.example.package_service_api.common.entities;

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
@Table(name = "packages")
public class PackageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID trackingNumber;

    private String address;

    private String content;

    private Double weight;

    private String receiverEmail;

    private UUID userId;
}
