package com.instaride.service;

import com.instaride.dto.JwtResponse;
import com.instaride.dto.LoginRequest;
import com.instaride.entity.User;
import com.instaride.enums.UserRole;
import com.instaride.repository.UserRepository;
import com.instaride.security.JwtTokenProvider;
import com.instaride.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Override
    public ResponseEntity<?> registerUser(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }
        if (userRepository.existsByPhoneNumber(signUpRequest.getPhoneNumber())) {
            return ResponseEntity.badRequest().body("Error: Phone number is already in use!");
        }

        UserRole role;
        try {
            role = UserRole.valueOf("ROLE_" + signUpRequest.getRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: Invalid role specified!");
        }

        User user = new User(
                signUpRequest.getName(),
                signUpRequest.getEmail(),
                signUpRequest.getPhoneNumber(),
                passwordEncoder.encode(signUpRequest.getPassword()),
                role);
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @Override
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();

        return ResponseEntity
                .ok(new JwtResponse(jwt, user.getId(), user.getEmail(), user.getName(), user.getRole().name()));
    }
}