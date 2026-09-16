package com.smartparking.parking.dto;

import com.smartparking.parking.entity.ParkingSource;
import com.smartparking.parking.entity.ParkingStatus;
import java.time.LocalDateTime;

public record ParkingSpotResponse(

        Long id,
        String name,
        Double latitude,
        Double longitude,
        ParkingStatus status,
        ParkingSource source,
        LocalDateTime lastUpdated

) {}