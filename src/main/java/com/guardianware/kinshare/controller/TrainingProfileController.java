package com.guardianware.kinshare.controller;

import com.guardianware.kinshare.dto.TrainingProfileDTO;
import com.guardianware.kinshare.service.TrainingProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/training-profile")
@CrossOrigin(origins = "${CORS_ALLOW_HOST}") // Get allowed origins from environment variable
public class TrainingProfileController {
    @Autowired
    private TrainingProfileService trainingProfileService;

    @PostMapping
    public ResponseEntity<String> saveTrainingProfile(@RequestBody TrainingProfileDTO trainingProfileDTO) {
        System.out.println("Request received: " + trainingProfileDTO); // Log the incoming request

        try {
            System.out.println("Received Training Profile: " + trainingProfileDTO); // Debugging log
            trainingProfileService.saveTrainingProfile(trainingProfileDTO);
            return ResponseEntity.ok("Training profile saved successfully!");
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save training profile.");
        }
    }

    @PostMapping("/test")
    public ResponseEntity<String> receiveTestMessage(@RequestBody Map<String, String> requestBody) {
        System.out.println("Message received from frontend: " + requestBody.get("message")); // Log the message

        try {
            return ResponseEntity.ok("Message received successfully!");
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return ResponseEntity.status(500).body("Failed to process the message: " + e.getMessage());
        }
    }
}