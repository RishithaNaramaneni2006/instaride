package com.instaride.service;

import com.instaride.dto.RideBookingRequest;
import com.instaride.dto.RideOptionResponse;
import com.instaride.entity.Ride;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface RideService {
    ResponseEntity<?> requestRide(String userEmail, RideBookingRequest rideBookingRequest);

    List<RideOptionResponse> getRideOptions(String area); // area might be city or lat/long

    List<Ride> getRiderHistory(String userEmail);
    // Add more methods for driver accepting ride, updating status etc.
}