package com.example.airportManager.dto;

public record AirportUpdateDTO(
        String iata,
        String icao,
        String name
) {
}
