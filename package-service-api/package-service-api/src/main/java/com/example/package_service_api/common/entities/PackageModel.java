package com.example.package_service_api.common.entities;

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
@Table(name = "packages")
public class PackageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long trackingNumber;

    private String address;

    private String content;

    private Double weight;

    private String receiverEmail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserModel userId;
}
