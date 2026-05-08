package com.example.letters_service_api.service.Interfaces;

import com.example.letters_service_api.common.dtos.CreateLetterRequestDTO;
import com.example.letters_service_api.common.dtos.UpdateLetterRequestDTO;
import com.example.letters_service_api.common.entities.LetterModel;

import java.util.List;

public interface ILetterService {
    LetterModel createLetter(CreateLetterRequestDTO packageDTO, String userId);
    LetterModel getLetterById(String userId, Long trackingNumber);
    List<LetterModel> getAllLetters(String userId);
    void updateLetter(UpdateLetterRequestDTO packageDTO, String userId, Long trackingNumber);
    void deleteLetter(String userId, Long trackingNumber);
}
