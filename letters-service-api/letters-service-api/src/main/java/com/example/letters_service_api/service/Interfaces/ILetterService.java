package com.example.letters_service_api.service.Interfaces;

import com.example.letters_service_api.common.dtos.CreateLetterRequestDTO;
import com.example.letters_service_api.common.dtos.UpdateLetterRequestDTO;
import com.example.letters_service_api.common.entities.LetterModel;

import java.util.List;
import java.util.UUID;

public interface ILetterService {
    LetterModel createLetter(CreateLetterRequestDTO packageDTO, UUID userId);
    LetterModel getLetterById(UUID userId, UUID trackingNumber);
    List<LetterModel> getAllLetters(UUID userId);
    void updateLetter(UpdateLetterRequestDTO packageDTO, UUID userId, UUID trackingNumber);
    void deleteLetter(UUID userId, UUID trackingNumber);
}
