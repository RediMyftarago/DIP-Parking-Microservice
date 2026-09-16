package com.smartparking.parking.dto;

public record ParkingEvent(

        Long spotId,
        Boolean occupied,
        String source,
        Long timestamp

) {}