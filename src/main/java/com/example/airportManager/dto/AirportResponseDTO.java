package com.example.airportManager.dto;

public record AirportResponseDTO(
        String iata,
        String icao,
        String name,
        String city,
        String country,
        String timezone
) {
}
