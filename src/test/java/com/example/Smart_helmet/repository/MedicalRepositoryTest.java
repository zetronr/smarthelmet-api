package com.example.Smart_helmet.repository;

import com.example.Smart_helmet.model.MedicInfo;
import com.example.Smart_helmet.model.UserInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class MedicalRepositoryTest {

    @Autowired
    private medicalRepository medicalRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void MedicalRepository_saveinfo() {
        // Arrange
        UserInfo user = new UserInfo();
        user.setEmail("test@email.com");
        user.setFullname("Test User");
        user.setUuid("abc123");
        user.setGender("male");
        user.setDateofbirth(LocalDate.of(2000, 1, 1));
        user.setPassword("password123");
        UserInfo savedUser = userRepository.save(user);

        MedicInfo medicInfo = new MedicInfo();
        medicInfo.setUserInfo(savedUser);  // wajib
        medicInfo.setBloodType("A+");
        medicInfo.setEmergencyContactName("John Doe");
        medicInfo.setEmergencyContactPhone("08123456789");
        medicInfo.setChronicDiseases("Hypertension");
        medicInfo.setAllergies("Peanuts");
        medicInfo.setCurrentMedications("Lisinopril");
        medicInfo.setImplantInfo("None");
        medicInfo.setIsPregnant(false);
        medicInfo.setHasDisability(true);
        medicInfo.setDisabilityNotes("Uses wheelchair");
        medicInfo.setMentalConditions("None");
        medicInfo.setPhotoUrl("http://example.com/photo.jpg");
        medicInfo.setNotes("Patient needs monthly checkups.");
        medicInfo.setLastUpdated(LocalDateTime.now());

        // Act
        MedicInfo savedMedic = medicalRepository.save(medicInfo);

        // Assert
        assertThat(savedMedic).isNotNull();
        assertThat(savedMedic.getId()).isNotNull();
        assertThat(savedMedic.getUserInfo().getEmail()).isEqualTo("test@email.com");
        assertThat(savedMedic.getBloodType()).isEqualTo("B+");
    }
}
