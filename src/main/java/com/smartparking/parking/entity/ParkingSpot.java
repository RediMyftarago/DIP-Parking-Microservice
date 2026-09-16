package com.smartparking.parking.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "parking_spots")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkingSpot {

    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    private Double latitude;

    private Double longitude;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ParkingStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ParkingSource source;

    private LocalDateTime lastUpdated;
}