package com.example.airportManager.dto;

public record RouteResponseDTO(
        String originAirport,
        String destAirport,
        int distanceNm,
        int stdDurationMin
) {
}
