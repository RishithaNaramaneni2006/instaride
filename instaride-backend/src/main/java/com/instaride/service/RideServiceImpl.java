package com.instaride.service;

import com.instaride.dto.RideBookingRequest;
import com.instaride.dto.RideOptionResponse;
import com.instaride.entity.Ride;
import com.instaride.entity.User;
import com.instaride.repository.RideRepository;
import com.instaride.repository.UserRepository;
import com.instaride.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RideServiceImpl implements RideService {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<?> requestRide(String userEmail, RideBookingRequest rideBookingRequest) {
        User rider = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Rider not found: " + userEmail));

        // Simplified price estimation
        double estimatedPrice = 0.0;
        if ("UberX".equalsIgnoreCase(rideBookingRequest.getVehicleType())) {
            estimatedPrice = 10.0; // Base price + distance factor (to be implemented)
        } else if ("UberXL".equalsIgnoreCase(rideBookingRequest.getVehicleType())) {
            estimatedPrice = 15.0;
        } else if ("Bike".equalsIgnoreCase(rideBookingRequest.getVehicleType())) {
            estimatedPrice = 5.0;
        } else {
            return ResponseEntity.badRequest().body("Invalid vehicle type");
        }

        Ride ride = new Ride(
                rider,
                rideBookingRequest.getPickupLocation(),
                rideBookingRequest.getDropoffLocation(),
                rideBookingRequest.getVehicleType(),
                estimatedPrice);
        rideRepository.save(ride);
        return ResponseEntity.ok(ride);
    }

    @Override
    public List<RideOptionResponse> getRideOptions(String area) {
        // For simplicity, returning fixed options. In a real app, this would query
        // based on area.
        return Arrays.asList(
                new RideOptionResponse("UberX", "Affordable, everyday rides", 1.0),
                new RideOptionResponse("UberXL", "Spacious rides for groups", 1.5),
                new RideOptionResponse("Bike", "Quick solo rides", 0.5));
    }

    @Override
    public List<Ride> getRiderHistory(String userEmail) {
        User rider = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Rider not found: " + userEmail));
        return rideRepository.findByRiderId(rider.getId());
    }
}