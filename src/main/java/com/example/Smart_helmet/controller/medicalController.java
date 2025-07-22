package com.example.Smart_helmet.controller;

import com.example.Smart_helmet.model.UsermedicalService;
import com.example.Smart_helmet.dto.medicalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medical")
public class medicalController {

    private final UsermedicalService medService;

    @Autowired
    public medicalController(UsermedicalService medService) {
        this.medService = medService;
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitMedicalinfo(@RequestBody medicalDTO mDTO,
                                         @RequestHeader("Authorization") String authHeader) {
        String jwt = authHeader.replace("Bearer ", "");
        String result = medService.submitMedicalinfo(mDTO, jwt);
        return ResponseEntity.ok(result);
    }


}
