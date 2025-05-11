package com.instaride.service;

import com.instaride.dto.UserProfileDto;
import org.springframework.http.ResponseEntity;

public interface AccountService {
    ResponseEntity<UserProfileDto> getRiderProfile(String email);

    ResponseEntity<UserProfileDto> updateRiderProfile(String email, UserProfileDto userProfileDto);
    // Similar methods for Driver
}
