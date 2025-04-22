package com.guardianware.kinshare.dto;

import java.util.List;

public class TrainingProfileDTO {
    private int userId;
    private String profileName;
    private int sophistication; // Renamed from sophisticationLevel
    private String frequency;
    private List<String> communicationChannel; // Ensure this is a List<String>
    private String timeOfCommunication;
    private List<String> communicationContext; // Ensure this is a List<String>

    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public int getSophistication() { // Updated getter
        return sophistication;
    }

    public void setSophistication(int sophistication) { // Updated setter
        this.sophistication = sophistication;
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

    @Override
    public String toString() {
        return "TrainingProfileDTO{" +
                "userId=" + userId +
                ", profileName='" + profileName + '\'' +
                ", sophistication=" + sophistication + // Updated field
                ", frequency='" + frequency + '\'' +
                ", communicationChannel=" + communicationChannel +
                ", timeOfCommunication='" + timeOfCommunication + '\'' +
                ", communicationContext=" + communicationContext +
                '}';
    }
}