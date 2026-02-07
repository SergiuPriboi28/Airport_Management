package com.example.airportManager.dto.flight;

import com.example.airportManager.model.FlightStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public record FlightSearchDTO(
        FlightType flightType,
        LocalDate departureScheduled,
        LocalDate arrivalScheduled,
        Optional<Long> routeId,
        FlightStatus flightStatus
) {
}
