package com.example.Smart_helmet.dto;


import java.time.LocalDateTime;

public class medicalDTO {
    private Long userId;

    private String bloodType;

    private String emergencyContactName;

    private String emergencyContactPhone;

    private String chronicDiseases;

    private String allergies;

    private String currentMedications;

    private String implantInfo;

    private Boolean isPregnant;

    private Boolean hasDisability;

    private String disabilityNotes;

    private String mentalConditions;

    private String photoUrl;

    private String notes;

    private LocalDateTime lastUpdated;

    public medicalDTO() {}

    // Getters & Setters
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBloodType() {
        return bloodType;
    }
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }
    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }
    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public String getChronicDiseases() {
        return chronicDiseases;
    }
    public void setChronicDiseases(String chronicDiseases) {
        this.chronicDiseases = chronicDiseases;
    }

    public String getAllergies() {
        return allergies;
    }
    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getCurrentMedications() {
        return currentMedications;
    }
    public void setCurrentMedications(String currentMedications) {
        this.currentMedications = currentMedications;
    }

    public String getImplantInfo() {
        return implantInfo;
    }
    public void setImplantInfo(String implantInfo) {
        this.implantInfo = implantInfo;
    }

    public Boolean getIsPregnant() {
        return isPregnant;
    }
    public void setIsPregnant(Boolean isPregnant) {
        this.isPregnant = isPregnant;
    }

    public Boolean getHasDisability() {
        return hasDisability;
    }
    public void setHasDisability(Boolean hasDisability) {
        this.hasDisability = hasDisability;
    }

    public String getDisabilityNotes() {
        return disabilityNotes;
    }
    public void setDisabilityNotes(String disabilityNotes) {
        this.disabilityNotes = disabilityNotes;
    }

    public String getMentalConditions() {
        return mentalConditions;
    }
    public void setMentalConditions(String mentalConditions) {
        this.mentalConditions = mentalConditions;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
