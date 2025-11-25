package com.example.airportManager.dto.airport;

public record AirportUpdateDTO(
        String iata,
        String icao,
        String name
) {
}
