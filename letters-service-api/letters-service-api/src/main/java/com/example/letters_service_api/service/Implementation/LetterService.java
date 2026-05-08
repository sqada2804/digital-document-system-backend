package com.example.letters_service_api.service.Implementation;

import com.example.letters_service_api.common.dtos.CreateLetterRequestDTO;
import com.example.letters_service_api.common.dtos.UpdateLetterRequestDTO;
import com.example.letters_service_api.common.entities.LetterModel;
import com.example.letters_service_api.common.exceptions.NotFoundException;
import com.example.letters_service_api.common.exceptions.UnauthorizedException;
import com.example.letters_service_api.repository.ILetterRepository;
import com.example.letters_service_api.service.Interfaces.ILetterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LetterService implements ILetterService {

    private final ILetterRepository letterRepository;

    public LetterService(ILetterRepository letterRepository) {
        this.letterRepository = letterRepository;
    }

    @Override
    public LetterModel createLetter(CreateLetterRequestDTO letterDTO, String userId) {
        return Optional.of(letterDTO)
                .map(letters -> mapToEntity(letters, userId))
                .map(letterRepository::save)
                .orElseThrow(() -> new UnauthorizedException("Unauthorized to create a letter"));
    }

    private LetterModel mapToEntity(CreateLetterRequestDTO letterDTO, String userId) {
        return LetterModel.builder()
                .address(letterDTO.getAddress())
                .body(letterDTO.getBody())
                .subject(letterDTO.getSubject())
                .receiverEmail(letterDTO.getReceiverEmail())
                .userId(userId)
                .build();
    }

    @Override
    public LetterModel getLetterById(String userId, Long trackingNumber) {
        return Optional.of(userId)
                .flatMap(userId1 -> letterRepository.findLetterByUserIdAndTrackingNumber(userId1, trackingNumber))
                .orElseThrow(() -> new RuntimeException("Error finding letter by id"));
    }

    @Override
    public List<LetterModel> getAllLetters(String userId) {
        return letterRepository.findAllByUserId(userId);
    }

    @Override
    public void updateLetter(UpdateLetterRequestDTO letterDTO, String userId, Long trackingNumber) {
        letterRepository.findLetterByUserIdAndTrackingNumber(userId, trackingNumber)
                .map(letterExists -> updateLetterFields(letterExists, letterDTO))
                .map(letterRepository::save)
                .orElseThrow(() -> new NotFoundException("Letter wasn't found to update"));
    }

    private LetterModel updateLetterFields(LetterModel letterExists, UpdateLetterRequestDTO letterDTO) {
        letterExists.setAddress(letterDTO.getAddress());
        letterExists.setBody(letterDTO.getBody());
        letterExists.setSubject(letterDTO.getSubject());
        letterExists.setReceiverEmail(letterDTO.getReceiverEmail());
        return letterExists;
    }

    @Override
    public void deleteLetter(String userId, Long trackingNumber) {
        letterRepository.findLetterByUserIdAndTrackingNumber(userId, trackingNumber)
                .ifPresentOrElse(letterRepository::delete, () -> {
                    throw new NotFoundException("Letter wasnt' found to delete");
                });
    }
}
