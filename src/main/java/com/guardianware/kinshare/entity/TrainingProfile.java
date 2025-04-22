package com.guardianware.kinshare.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "training_profiles")
public class TrainingProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "profile_name", nullable = false, length = 100)
    private String profileName;

    @Column(name = "sophistication_level", nullable = false)
    private int sophisticationLevel;

    @Column(name = "frequency", nullable = false, length = 20)
    private String frequency;

    @Column(name = "communication_channel", nullable = false, columnDefinition = "JSON")
    private List<String> communicationChannel;

    @Column(name = "time_of_communication", nullable = false, length = 50)
    private String timeOfCommunication;

    @Column(name = "communication_context", nullable = false, columnDefinition = "JSON")
    private List<String> communicationContext;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public int getSophistication() { // Renamed from getSophisticationLevel
        return sophisticationLevel;
    }

    public void setSophistication(int sophistication) { // Renamed from setSophisticationLevel
        this.sophisticationLevel = sophistication;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public List<String> getCommunicationChannel() {
        return communicationChannel;
    }

    public void setCommunicationChannel(List<String> communicationChannel) {
        this.communicationChannel = communicationChannel;
    }

    public String getTimeOfCommunication() {
        return timeOfCommunication;
    }

    public void setTimeOfCommunication(String timeOfCommunication) {
        this.timeOfCommunication = timeOfCommunication;
    }

    public List<String> getCommunicationContext() {
        return communicationContext;
    }

    public void setCommunicationContext(List<String> communicationContext) {
        this.communicationContext = communicationContext;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
