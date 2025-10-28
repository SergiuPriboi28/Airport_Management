package com.example.airportManager.dto;

import com.example.airportManager.model.FlightStatus;

import java.time.LocalDateTime;

public record FlightResponseDTO(
        String code,
        LocalDateTime departureScheduled,
        LocalDateTime arrivalScheduled,
        String gate,
        String aircraft,
        String route,
        FlightStatus flightStatus
) {
}
