package com.example.airportManager.dto;

import com.example.airportManager.model.FlightStatus;

import java.time.LocalDateTime;

public record FlightUpdateDTO(
        LocalDateTime departureScheduled,
        LocalDateTime arrivalScheduled,
        String gate,
        String aircraft,
        String route,
        FlightStatus flightStatus
) {
}
