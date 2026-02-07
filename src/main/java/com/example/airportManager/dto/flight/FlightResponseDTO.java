package com.example.airportManager.dto.flight;

import com.example.airportManager.model.FlightStatus;

import java.time.LocalDateTime;
import java.util.List;

public record FlightResponseDTO(
        String code,
        LocalDateTime departureScheduled,
        LocalDateTime arrivalScheduled,
        String gate,
        String aircraftId,
        String routeId,
        FlightStatus flightStatus,
        List<FlightResponseDTO> departureFlights,
        List<FlightResponseDTO> returnFlights
) {
}
