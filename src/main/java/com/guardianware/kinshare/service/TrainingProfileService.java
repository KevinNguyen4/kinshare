package com.guardianware.kinshare.service;

import com.guardianware.kinshare.dto.TrainingProfileDTO;
import com.guardianware.kinshare.entity.TrainingProfile;
import com.guardianware.kinshare.repository.TrainingProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class TrainingProfileService {

    @Autowired
    private TrainingProfileRepository trainingProfileRepository;

    public List<TrainingProfileDTO> getAllTrainingProfiles() {
        return trainingProfileRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TrainingProfileDTO getTrainingProfileById(Long id) {
        return trainingProfileRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public TrainingProfileDTO saveTrainingProfile(TrainingProfileDTO trainingProfileDTO) {
        Optional<TrainingProfile> existingProfileOpt = trainingProfileRepository
                .findByUserId(trainingProfileDTO.getUserId());

        TrainingProfile trainingProfile;
        TrainingProfile savedProfile = null;

        if (existingProfileOpt.isPresent()) {
            // Update the existing profile
            TrainingProfile existingProfile = existingProfileOpt.get();
            trainingProfile = convertToEntity(trainingProfileDTO);
            trainingProfile.setId(existingProfile.getId());
            savedProfile = trainingProfileRepository.updateTrainingProfileByUserId(
                    trainingProfile.getUserId(),
                    trainingProfile.getProfileName(),
                    trainingProfile.getSophistication(),
                    trainingProfile.getFrequency(),
                    trainingProfile.getTimeOfCommunication(),
                    trainingProfile.getCommunicationChannel(),
                    trainingProfile.getCommunicationContext());
        } else {
            // Create a new profile
            trainingProfile = convertToEntity(trainingProfileDTO);
            savedProfile = trainingProfileRepository.save(trainingProfile);
        }

        return convertToDTO(savedProfile);
    }

    public TrainingProfileDTO updateTrainingProfile(Long id, TrainingProfileDTO trainingProfileDTO) {
        if (trainingProfileRepository.existsById(id)) {
            TrainingProfile trainingProfile = convertToEntity(trainingProfileDTO);
            trainingProfile.setId(id);
            TrainingProfile updatedProfile = trainingProfileRepository.save(trainingProfile);
            return convertToDTO(updatedProfile);
        }
        return null;
    }

    public void deleteTrainingProfile(Long id) {
        if (trainingProfileRepository.existsById(id)) {
            trainingProfileRepository.deleteById(id);
        }
    }

    private TrainingProfileDTO convertToDTO(TrainingProfile trainingProfile) {
        TrainingProfileDTO dto = new TrainingProfileDTO();
        dto.setUserId(trainingProfile.getUserId().intValue());
        dto.setProfileName(trainingProfile.getProfileName());
        dto.setSophistication(trainingProfile.getSophistication());
        dto.setFrequency(trainingProfile.getFrequency());
        try {
            // Convert String to List<String> for communicationChannel and
            // communicationContext
            // Assuming the String is a comma-separated list
            dto.setCommunicationChannel(List.of(trainingProfile.getCommunicationChannel().split(",")));
            dto.setCommunicationContext(List.of(trainingProfile.getCommunicationContext().split(",")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        dto.setTimeOfCommunication(trainingProfile.getTimeOfCommunication());
        return dto;
    }

    private TrainingProfile convertToEntity(TrainingProfileDTO dto) {
        TrainingProfile trainingProfile = new TrainingProfile();
        trainingProfile.setUserId((long) dto.getUserId());
        trainingProfile.setProfileName(dto.getProfileName());
        trainingProfile.setSophistication(dto.getSophistication());
        trainingProfile.setFrequency(dto.getFrequency());
        trainingProfile.setTimeOfCommunication(dto.getTimeOfCommunication());
        // Convert List<String> to String for communicationChannel and
        // communicationContext
        trainingProfile.setCommunicationChannel(String.join(",", dto.getCommunicationChannel()));
        trainingProfile.setCommunicationContext(String.join(",", dto.getCommunicationContext()));
        return trainingProfile;
    }
}
