package com.example.letters_service_api.repository;

import com.example.letters_service_api.common.entities.LetterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ILetterRepository extends JpaRepository<LetterModel, Long> {
    Optional<LetterModel> findLetterByUserIdAndTrackingNumber(@Param("userId")String userId, @Param("{trackingNumber}")Long trackingNumber);
    List<LetterModel> findAllByUserId(@Param("userId") String userId);

}
