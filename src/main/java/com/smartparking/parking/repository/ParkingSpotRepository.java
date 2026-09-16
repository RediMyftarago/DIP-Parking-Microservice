package com.smartparking.parking.repository;

import com.smartparking.parking.entity.ParkingSpot;
import com.smartparking.parking.entity.ParkingStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {

    List<ParkingSpot> findByStatus(ParkingStatus status);

}
