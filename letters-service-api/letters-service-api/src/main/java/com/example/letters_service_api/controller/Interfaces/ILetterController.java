package com.example.letters_service_api.controller.Interfaces;

import com.example.letters_service_api.common.constants.ApiPathConstants;
import com.example.letters_service_api.common.dtos.CreateLetterRequestDTO;
import com.example.letters_service_api.common.dtos.UpdateLetterRequestDTO;
import com.example.letters_service_api.common.entities.LetterModel;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.LETTER_ROUTE)
public interface ILetterController {
    @PostMapping
    ResponseEntity<LetterModel> createLetter(@RequestBody CreateLetterRequestDTO letterDTO, @AuthenticationPrincipal Jwt jwt);

    @GetMapping(value = "/{trackingNumber}")
    ResponseEntity<LetterModel> getLetter(@AuthenticationPrincipal Jwt jwt, @PathVariable UUID trackingNumber);

    @GetMapping()
    ResponseEntity<List<LetterModel> >getAllLetters(@AuthenticationPrincipal Jwt jwt);

    @PutMapping(value = "/{trackingNumber}")
    ResponseEntity<Void> updateLetter(@RequestBody UpdateLetterRequestDTO letterDTO, @AuthenticationPrincipal Jwt jwt, @PathVariable UUID trackingNumber);

    @DeleteMapping(value = "/{trackingNumber}")
    ResponseEntity<Void> deleteLetter(@AuthenticationPrincipal Jwt jwt, @PathVariable UUID trackingNumber);
}
