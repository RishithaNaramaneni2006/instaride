package com.instaride.controller;

import com.instaride.dto.LoginRequest;
import com.instaride.dto.SignUpRequest;
import com.instaride.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600) // Allow all origins for development
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/rider/signup")
    public ResponseEntity<?> registerRider(@Valid @RequestBody SignUpRequest signUpRequest) {
        signUpRequest.setRole("RIDER"); // Ensure role is set for rider signup
        return authService.registerUser(signUpRequest);
    }

    @PostMapping("/rider/login")
    public ResponseEntity<?> authenticateRider(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @PostMapping("/driver/signup")
    public ResponseEntity<?> registerDriver(@Valid @RequestBody SignUpRequest signUpRequest) {
        signUpRequest.setRole("DRIVER"); // Ensure role is set for driver signup
        return authService.registerUser(signUpRequest);
    }

    @PostMapping("/driver/login")
    public ResponseEntity<?> authenticateDriver(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }
}