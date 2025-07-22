package com.example.Smart_helmet.repository;
import com.example.Smart_helmet.model.MedicInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface medicalRepository extends JpaRepository<MedicInfo,Long> {
}
