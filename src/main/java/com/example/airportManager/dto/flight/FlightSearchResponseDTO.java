package com.example.airportManager.dto.flight;

import com.example.airportManager.model.Airport;
import com.example.airportManager.model.Flight;

import java.time.LocalDateTime;
import java.util.List;

public record FlightSearchResponseDTO(
//        LocalDateTime departureScheduled,
//        LocalDateTime arrivalScheduled,
//        Airport originAirport,
//        Airport destAirport,
//        int stdDurationMin
        List<FlightResponseDTO> departureFlights,
        List<FlightResponseDTO> returnFlights
) {
}
