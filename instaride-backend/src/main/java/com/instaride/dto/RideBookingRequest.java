package com.instaride.dto;

import lombok.Data;

@Data
public class RideBookingRequest {
    private String pickupLocation;
    private String dropoffLocation;
    private String vehicleType; // e.g., "UberX", "UberXL"
}