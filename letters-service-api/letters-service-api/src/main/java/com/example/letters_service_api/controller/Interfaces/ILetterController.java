package com.example.letters_service_api.controller.Interfaces;

import com.example.letters_service_api.common.constants.ApiPathConstants;
import com.example.letters_service_api.common.dtos.CreateLetterRequestDTO;
import com.example.letters_service_api.common.dtos.UpdateLetterRequestDTO;
import com.example.letters_service_api.common.entities.LetterModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.LETTER_ROUTE)
public interface ILetterController {
    @PostMapping
    ResponseEntity<LetterModel> createLetter(@RequestBody CreateLetterRequestDTO letterDTO, @RequestHeader("X-User-Id") String userId);

    @GetMapping(value = "/{trackingNumber}")
    ResponseEntity<LetterModel> getLetter(@RequestHeader("X-User-Id") String userId, @PathVariable Long trackingNumber);

    @GetMapping()
    ResponseEntity<List<LetterModel> >getAllLetters(@RequestHeader("X-User-Id") String userId);

    @PutMapping(value = "/{trackingNumber}")
    ResponseEntity<Void> updateLetter(@RequestBody UpdateLetterRequestDTO letterDTO, @RequestHeader("X-User-Id") String userId, @PathVariable Long trackingNumber);

    @DeleteMapping(value = "/{trackingNumber}")
    ResponseEntity<Void> deleteLetter(@RequestHeader("X-User-Id") String userId, @PathVariable Long trackingNumber);
}
