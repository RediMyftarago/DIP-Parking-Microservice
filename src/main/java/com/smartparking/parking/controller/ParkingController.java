package com.smartparking.parking.controller;


import com.smartparking.parking.dto.ParkingSpotResponse;
import com.smartparking.parking.service.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/parking-spots")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;

    @GetMapping
    public ResponseEntity<List<ParkingSpotResponse>> getAllSpots() {
        return ResponseEntity.ok(parkingService.getAllSpots());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpotResponse> getSpot(@PathVariable Long id) {
        return ResponseEntity.ok(parkingService.getSpot(id));
    }

    @GetMapping("/free")
    public ResponseEntity<List<ParkingSpotResponse>> getFreeSpots() {
        return ResponseEntity.ok(parkingService.getFreeSpots());
    }
}