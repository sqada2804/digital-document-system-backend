package com.example.package_service_api.common.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreatePackageRequestDTO {
    @NotNull
    private String address;
    @NotNull
    private String content;
    @NotNull
    private String weight;
    @NotNull
    private String receiverEmail;
}
