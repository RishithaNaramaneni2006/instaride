package com.instaride.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "rides")
@Data
@NoArgsConstructor
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rider_id", nullable = false)
    private User rider;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = true) // Driver can be assigned later
    private User driver;

    private String pickupLocation;
    private String dropoffLocation;
    private String vehicleType; // e.g., UberX, UberXL
    private Double estimatedPrice;
    private LocalDateTime requestTime;
    private LocalDateTime pickupTime; // Actual pickup
    private LocalDateTime dropoffTime; // Actual dropoff
    private String status; // e.g., REQUESTED, ACCEPTED, IN_PROGRESS, COMPLETED, CANCELLED

    public Ride(User rider, String pickupLocation, String dropoffLocation, String vehicleType, Double estimatedPrice) {
        this.rider = rider;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.vehicleType = vehicleType;
        this.estimatedPrice = estimatedPrice;
        this.requestTime = LocalDateTime.now();
        this.status = "REQUESTED";
    }
}