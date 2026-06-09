package com.example.letters_service_api.service.Implementation;

import com.example.letters_service_api.common.constants.TopicConstants;
import com.example.letters_service_api.common.dtos.CreateLetterRequestDTO;
import com.example.letters_service_api.common.dtos.UpdateLetterRequestDTO;
import com.example.letters_service_api.common.entities.LetterModel;
import com.example.letters_service_api.common.exceptions.NotFoundException;
import com.example.letters_service_api.common.exceptions.UnauthorizedException;
import com.example.letters_service_api.repository.ILetterRepository;
import com.example.letters_service_api.service.Interfaces.ILetterService;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LetterService implements ILetterService {

    private final ILetterRepository letterRepository;
    private final StreamBridge streamBridge;

    public LetterService(ILetterRepository letterRepository, StreamBridge streamBridge) {
        this.letterRepository = letterRepository;
        this.streamBridge = streamBridge;
    }

    @Override
    public LetterModel createLetter(CreateLetterRequestDTO letterDTO, UUID userId) {
        return Optional.of(letterDTO)
                .map(letters -> mapToEntity(letters, userId))
                .map(letterRepository::save)
                .map(this::sendLetterEvent)
                .orElseThrow(() -> new UnauthorizedException("Error creating letter"));
    }

    private LetterModel sendLetterEvent(LetterModel letterModel){
        Optional.of(letterModel)
                .map(givenLetter -> this.streamBridge.send(TopicConstants.LETTER_CREATED_TOPIC, letterModel))
                .map(bool -> letterModel);
        return letterModel;
    }

    private LetterModel mapToEntity(CreateLetterRequestDTO letterDTO, UUID userId) {
        return LetterModel.builder()
                .address(letterDTO.getAddress())
                .body(letterDTO.getBody())
                .subject(letterDTO.getSubject())
                .receiverEmail(letterDTO.getReceiverEmail())
                .userId(userId)
                .build();
    }

    @Override
    public LetterModel getLetterById(UUID userId, UUID trackingNumber) {
        return letterRepository.findLetterByUserIdAndTrackingNumber(userId, trackingNumber)
                .orElseThrow(() ->  new NotFoundException("Letter wasn't found to show"));
    }

    @Override
    public List<LetterModel> getAllLetters(UUID userId) {
        return letterRepository.findAllByUserId(userId);
    }

    @Override
    public void updateLetter(UpdateLetterRequestDTO letterDTO, UUID userId, UUID trackingNumber) {
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
    public void deleteLetter(UUID userId, UUID trackingNumber) {
        letterRepository.findLetterByUserIdAndTrackingNumber(userId, trackingNumber)
                .ifPresentOrElse(letterRepository::delete, () -> {
                    throw new NotFoundException("Letter wasnt' found to delete");
                });
    }
}
