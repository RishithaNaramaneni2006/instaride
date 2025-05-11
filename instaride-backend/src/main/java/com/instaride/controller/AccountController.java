package com.instaride.controller;

import com.instaride.dto.UserProfileDto;
import com.instaride.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/rider")
    @PreAuthorize("hasRole('RIDER')")
    public ResponseEntity<UserProfileDto> getRiderProfile(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return accountService.getRiderProfile(userDetails.getUsername());
    }

    @PutMapping("/rider")
    @PreAuthorize("hasRole('RIDER')")
    public ResponseEntity<UserProfileDto> updateRiderProfile(Authentication authentication,
            @Valid @RequestBody UserProfileDto userProfileDto) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return accountService.updateRiderProfile(userDetails.getUsername(), userProfileDto);
    }

    // Similar GET and PUT endpoints for /driver if needed
}
