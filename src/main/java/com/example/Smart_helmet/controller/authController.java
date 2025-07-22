package com.example.Smart_helmet.controller;

import com.example.Smart_helmet.model.UserAuthservice;
import com.example.Smart_helmet.dto.registerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class authController {

    private final UserAuthservice userservice;
    @Autowired
    public authController(UserAuthservice userservice) {
        this.userservice = userservice;
    }


    @PostMapping("/register")
    public ResponseEntity<String> signup(@RequestBody registerDTO dto) {
        String response = userservice.register(dto);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> body) {
        try {
            String token = userservice.login(body.get("email"), body.get("password")).toString();
            return ResponseEntity.ok(token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login gagal: " + e.getMessage());
        }
    }


}
