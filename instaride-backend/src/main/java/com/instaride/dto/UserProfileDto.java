package com.instaride.dto;

import lombok.Data;

@Data
public class UserProfileDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String savedPlaces; // JSON string
}
