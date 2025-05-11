package com.instaride.dto;

import lombok.Data;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String email;
    private String name;
    private String role;

    public JwtResponse(String token, Long id, String email, String name, String role) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
    }
}
