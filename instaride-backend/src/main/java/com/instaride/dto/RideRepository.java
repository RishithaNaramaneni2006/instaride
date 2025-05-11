package com.instaride.dto;

import com.instaride.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findByRiderId(Long riderId);

    List<Ride> findByDriverId(Long driverId);
}