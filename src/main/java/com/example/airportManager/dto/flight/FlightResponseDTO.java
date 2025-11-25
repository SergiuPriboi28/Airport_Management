package com.example.airportManager.dto.flight;

import com.example.airportManager.model.FlightStatus;

import java.time.LocalDateTime;

public record FlightResponseDTO(
        String code,
        LocalDateTime departureScheduled,
        LocalDateTime arrivalScheduled,
        String gate,
        String aircraftId,
        String routeId,
        FlightStatus flightStatus
) {
}
