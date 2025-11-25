package com.example.airportManager.dto.flight;

import com.example.airportManager.model.FlightStatus;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record FlightUpdateDTO(
        LocalDateTime departureScheduled,
        LocalDateTime arrivalScheduled,
        String code,
        String gate,
        FlightStatus flightStatus,
        Long routeId,
        Long aircraftId
) {
}
