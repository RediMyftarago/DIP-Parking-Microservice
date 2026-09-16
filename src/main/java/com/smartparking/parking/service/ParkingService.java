package com.smartparking.parking.service;

import com.smartparking.parking.dto.ParkingEvent;
import com.smartparking.parking.dto.ParkingSpotResponse;
import com.smartparking.parking.entity.ParkingSource;
import com.smartparking.parking.entity.ParkingSpot;
import com.smartparking.parking.entity.ParkingStatus;
import com.smartparking.parking.repository.ParkingSpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingService {
    private final ParkingSpotRepository parkingSpotRepository;

    public void processEvent(ParkingEvent event) {
        ParkingSpot parkingSpot = parkingSpotRepository
                        .findById(event.spotId())
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Parking spot not found: "
                                                + event.spotId()
                                )
                        );

        ParkingSource eventSource = ParkingSource.valueOf(event.source().toUpperCase());

        //Nje parking spot i merren gjendje vetem nga nje burim kamer/sensor
        if (parkingSpot.getSource() != eventSource) {
            return;
        }
        ParkingStatus newStatus = event.occupied() ? ParkingStatus.OCCUPIED : ParkingStatus.FREE;
        parkingSpot.setStatus(newStatus);
        parkingSpot.setLastUpdated(LocalDateTime.ofInstant(Instant.ofEpochMilli(event.timestamp()), ZoneId.systemDefault())
        );
        parkingSpotRepository.save(parkingSpot);
    }

    public List<ParkingSpotResponse> getAllSpots() {

        return parkingSpotRepository
                .findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ParkingSpotResponse getSpot(Long id) {

        ParkingSpot spot = parkingSpotRepository
                        .findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Vendi i parkimit nuk u gjet"
                                )
                        );
        return mapToResponse(spot);
    }

    public List<ParkingSpotResponse> getFreeSpots() {
        return parkingSpotRepository
                .findByStatus(ParkingStatus.FREE)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private ParkingSpotResponse mapToResponse(ParkingSpot spot) {
        return new ParkingSpotResponse(
                spot.getId(),
                spot.getName(),
                spot.getLatitude(),
                spot.getLongitude(),
                spot.getStatus(),
                spot.getSource(),
                spot.getLastUpdated()
        );
    }
}