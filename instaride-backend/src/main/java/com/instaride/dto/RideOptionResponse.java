package com.instaride.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RideOptionResponse {
    private String typeName; // "UberX", "UberXL", "Bike"
    private String description;
    private double estimatedPriceFactor; // For simplicity, could be more complex
}
