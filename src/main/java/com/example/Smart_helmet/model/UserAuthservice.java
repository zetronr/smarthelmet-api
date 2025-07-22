package com.example.Smart_helmet.model;

import com.example.Smart_helmet.dto.registerDTO;
import com.example.Smart_helmet.repository.UserRepository;
import com.example.Smart_helmet.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserAuthservice {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String register(registerDTO dto) {
        if (userRepo.findByEmail(dto.getEmail()).isPresent()) {
            return "Email sudah terdaftar";
        }
        if (userRepo.findByUuid(dto.getUuid()).isPresent()){
            return "uuid sudah terdaftar";
        }
        UserInfo user = new UserInfo();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFullname(dto.getFullname());
        user.setDateofbirth(dto.getDob());
        user.setGender(dto.getGender());
        user.setUuid(dto.getUuid());
        userRepo.save(user);
        return "Berhasil Terdaftar";
    }

    public Map<String, String> login(String email, String password) {
        Optional<UserInfo> userOpt = userRepo.findByEmail(email);
        if (userOpt.isPresent()) {
            UserInfo user = userOpt.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                String token = jwtUtil.generateToken(user.getEmail());

                Map<String, String> response = new HashMap<>();
                response.put("token", token);
                response.put("fullname", user.getFullname());
                response.put("email", user.getEmail());

                return response;
            }
        }
        throw new RuntimeException("Invalid email/password");
    }

    public UserInfo getUserByDeviceId(String deviceId) {
        return userRepo.findByUuid(deviceId)
                .orElseThrow(() -> new RuntimeException("User not found for device ID: " + deviceId));
    }


}
