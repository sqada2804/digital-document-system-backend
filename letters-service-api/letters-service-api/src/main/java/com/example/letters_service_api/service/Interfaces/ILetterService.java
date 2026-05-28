package com.example.letters_service_api.service.Interfaces;

import com.example.letters_service_api.common.dtos.CreateLetterRequestDTO;
import com.example.letters_service_api.common.dtos.UpdateLetterRequestDTO;
import com.example.letters_service_api.common.entities.LetterModel;

import java.util.List;

public interface ILetterService {
    LetterModel createLetter(CreateLetterRequestDTO packageDTO, Long userId);
    LetterModel getLetterById(Long userId, Long trackingNumber);
    List<LetterModel> getAllLetters(Long userId);
    void updateLetter(UpdateLetterRequestDTO packageDTO, Long userId, Long trackingNumber);
    void deleteLetter(Long userId, Long trackingNumber);
}
