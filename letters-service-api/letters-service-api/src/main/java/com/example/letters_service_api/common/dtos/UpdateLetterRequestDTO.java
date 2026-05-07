package com.example.letters_service_api.common.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdateLetterRequestDTO {
    @NotNull
    private String address;

    @NotNull
    private String subject;

    @NotNull
    private String body;

    @NotNull
    private String receiverEmail;
}
