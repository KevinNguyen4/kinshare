package com.guardianware.kinshare.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guardianware.kinshare.dto.TrainingProfileDTO;
import com.guardianware.kinshare.entity.TrainingProfile;
import com.guardianware.kinshare.repository.TrainingProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingProfileService {

    @Autowired
    private TrainingProfileRepository trainingProfileRepository;

    private final ObjectMapper objectMapper = new ObjectMapper(); // For JSON conversion

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

    public TrainingProfileDTO createTrainingProfile(TrainingProfileDTO trainingProfileDTO) {
        TrainingProfile trainingProfile = convertToEntity(trainingProfileDTO);
        TrainingProfile savedProfile = trainingProfileRepository.save(trainingProfile);
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
            dto.setCommunicationChannel(objectMapper.readValue(trainingProfile.getCommunicationChannel().toString(), List.class)); // Convert JSON to List
            dto.setCommunicationContext(objectMapper.readValue(trainingProfile.getCommunicationContext().toString(), List.class)); // Convert JSON to List
        } catch (JsonProcessingException e) {
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
            trainingProfile.setCommunicationChannel(dto.getCommunicationChannel()); // Set List<String> directly
            trainingProfile.setCommunicationContext(dto.getCommunicationContext()); // Set List<String> directly
        trainingProfile.setTimeOfCommunication(dto.getTimeOfCommunication());
        return trainingProfile;
    }
}
