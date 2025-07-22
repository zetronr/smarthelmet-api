package com.example.Smart_helmet.model;

import com.example.Smart_helmet.dto.medicalDTO;
import com.example.Smart_helmet.repository.UserRepository;
import com.example.Smart_helmet.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsermedicalService {

    @Autowired
    private UserRepository uP;

    @Autowired
    private JwtUtil jwtUtil;

    public String submitMedicalinfo(medicalDTO mDTO, String jwt) {
        String email = jwtUtil.extractEmail(jwt);
        Optional<UserInfo> userOpt = uP.findByEmail(email);

        if (userOpt.isEmpty()) {
            return "email not found";
        }

        UserInfo user = userOpt.get();

        MedicInfo medicInfo = new MedicInfo();
        medicInfo.setUserInfo(user);
        medicInfo.setBloodType(mDTO.getBloodType());
        medicInfo.setEmergencyContactName(mDTO.getEmergencyContactName());
        medicInfo.setEmergencyContactPhone(mDTO.getEmergencyContactPhone());
        medicInfo.setChronicDiseases(mDTO.getChronicDiseases());
        medicInfo.setAllergies(mDTO.getAllergies());
        medicInfo.setCurrentMedications(mDTO.getCurrentMedications());
        medicInfo.setImplantInfo(mDTO.getImplantInfo());
        medicInfo.setIsPregnant(mDTO.getIsPregnant());
        medicInfo.setHasDisability(mDTO.getHasDisability());
        medicInfo.setDisabilityNotes(mDTO.getDisabilityNotes());
        medicInfo.setMentalConditions(mDTO.getMentalConditions());
        medicInfo.setPhotoUrl(mDTO.getPhotoUrl());
        medicInfo.setNotes(mDTO.getNotes());
        medicInfo.setLastUpdated(java.time.LocalDateTime.now());

        user.setMedicalProfile(medicInfo);

        uP.save(user);

        return "Medical info saved successfully for user " + email;
    }
}
