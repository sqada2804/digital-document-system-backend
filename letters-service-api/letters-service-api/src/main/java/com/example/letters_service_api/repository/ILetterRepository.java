package com.example.letters_service_api.repository;

import com.example.letters_service_api.common.entities.LetterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ILetterRepository extends JpaRepository<LetterModel, UUID> {
    Optional<LetterModel> findLetterByUserIdAndTrackingNumber(@Param("userId") UUID userId, @Param("{trackingNumber}")UUID trackingNumber);
    List<LetterModel> findAllByUserId(@Param("userId") UUID userId);

}
