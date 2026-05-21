package com.example.letters_service_api.controller.Implementation;

import com.example.letters_service_api.common.dtos.CreateLetterRequestDTO;
import com.example.letters_service_api.common.dtos.UpdateLetterRequestDTO;
import com.example.letters_service_api.common.entities.LetterModel;
import com.example.letters_service_api.controller.Interfaces.ILetterController;
import com.example.letters_service_api.service.Interfaces.ILetterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LetterController implements ILetterController {

    private final ILetterService letterService;

    public LetterController(ILetterService letterService) {
        this.letterService = letterService;
    }

    @Override
    public ResponseEntity<LetterModel> createLetter(CreateLetterRequestDTO letterDTO, String userId) {
        return ResponseEntity.ok(letterService.createLetter(letterDTO, userId));
    }

    @Override
    public ResponseEntity<LetterModel> getLetter(String userId, Long trackingNumber) {
        return ResponseEntity.ok(letterService.getLetterById(userId, trackingNumber));
    }

    @Override
    public ResponseEntity<List<LetterModel>> getAllLetters(String userId) {
        return ResponseEntity.ok(letterService.getAllLetters(userId));
    }

    @Override
    public ResponseEntity<Void> updateLetter(UpdateLetterRequestDTO letterDTO, String userId, Long trackingNumber) {
        letterService.updateLetter(letterDTO, userId, trackingNumber);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteLetter(String userId, Long trackingNumber) {
        letterService.deleteLetter(userId, trackingNumber);
        return ResponseEntity.noContent().build();
    }
}
