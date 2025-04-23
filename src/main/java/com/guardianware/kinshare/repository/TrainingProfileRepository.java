package com.guardianware.kinshare.repository;

import com.guardianware.kinshare.entity.TrainingProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Repository
public interface TrainingProfileRepository extends JpaRepository<TrainingProfile, Long> {
    Optional<TrainingProfile> findByUserId(int userId);

    // Method to update specific fields of a training profile by userId and return
    // the updated profile
    @Modifying
    @Query("UPDATE TrainingProfile t SET t.profileName = :profileName, t.sophisticationLevel = :sophisticationLevel, " +
            "t.frequency = :frequency, t.timeOfCommunication = :timeOfCommunication, " +
            "t.communicationChannel = :communicationChannel, t.communicationContext = :communicationContext, " +
            "t.updatedAt = CURRENT_TIMESTAMP " +
            "WHERE t.userId = :userId")
    @Transactional
    int updateTrainingProfileFields(@Param("userId") long userId,
            @Param("profileName") String profileName,
            @Param("sophisticationLevel") int sophisticationLevel,
            @Param("frequency") String frequency,
            @Param("timeOfCommunication") String timeOfCommunication,
            @Param("communicationChannel") String communicationChannel,
            @Param("communicationContext") String communicationContext);

    // Custom method that updates and returns the updated entity
    @Transactional
    default TrainingProfile updateTrainingProfileByUserId(long userId,
            String profileName,
            int sophisticationLevel,
            String frequency,
            String timeOfCommunication,
            String communicationChannel,
            String communicationContext) {

        updateTrainingProfileFields(userId, profileName, sophisticationLevel, frequency,
                timeOfCommunication, communicationChannel, communicationContext);

        return findByUserId((int) userId).orElse(null);
    }
}