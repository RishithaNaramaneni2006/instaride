package com.instaride.service;

import com.instaride.dto.LoginRequest;
import com.instaride.dto.SignUpRequest;
import com.instaride.dto.JwtResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> registerUser(SignUpRequest signUpRequest);

    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
}
