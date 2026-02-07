package com.example.airportManager.dto.flight;

import com.example.airportManager.model.Airport;

import java.time.LocalDateTime;

public record FlightSearchResponseDTO(
        LocalDateTime departureScheduled,
        LocalDateTime arrivalScheduled,
        Airport originAirport,
        Airport destAirport,
        int stdDurationMin
) {
}
