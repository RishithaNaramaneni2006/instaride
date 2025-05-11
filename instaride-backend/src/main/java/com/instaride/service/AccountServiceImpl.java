package com.instaride.service;

import com.instaride.dto.UserProfileDto;
import com.instaride.entity.User;
import com.instaride.repository.UserRepository;
import com.instaride.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<UserProfileDto> getRiderProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        UserProfileDto profileDto = new UserProfileDto();
        profileDto.setId(user.getId());
        profileDto.setName(user.getName());
        profileDto.setEmail(user.getEmail());
        profileDto.setPhoneNumber(user.getPhoneNumber());
        profileDto.setSavedPlaces(user.getSavedPlaces());

        return ResponseEntity.ok(profileDto);
    }

    @Override
    public ResponseEntity<UserProfileDto> updateRiderProfile(String email, UserProfileDto userProfileDto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        // Only update fields that are allowed/present in DTO
        if (userProfileDto.getName() != null)
            user.setName(userProfileDto.getName());
        if (userProfileDto.getPhoneNumber() != null)
            user.setPhoneNumber(userProfileDto.getPhoneNumber());
        if (userProfileDto.getSavedPlaces() != null)
            user.setSavedPlaces(userProfileDto.getSavedPlaces());
        // Email typically shouldn't be changed easily, or requires verification.
        // Password change needs a separate flow.

        User updatedUser = userRepository.save(user);

        UserProfileDto updatedProfileDto = new UserProfileDto();
        updatedProfileDto.setId(updatedUser.getId());
        updatedProfileDto.setName(updatedUser.getName());
        updatedProfileDto.setEmail(updatedUser.getEmail());
        updatedProfileDto.setPhoneNumber(updatedUser.getPhoneNumber());
        updatedProfileDto.setSavedPlaces(updatedUser.getSavedPlaces());

        return ResponseEntity.ok(updatedProfileDto);
    }
}