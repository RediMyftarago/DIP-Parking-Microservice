package com.smartparking.parking.kafka;

import com.smartparking.parking.dto.ParkingEvent;
import com.smartparking.parking.service.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParkingEventConsumer {
    private final ParkingService parkingService;

    @KafkaListener(topics = {"${kafka.topic.iot}", "${kafka.topic.cv}"}, groupId = "parking-service-group")
    public void consume(ParkingEvent event) {
        parkingService.processEvent(event);
    }
}